package com.elon.shape;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import com.elon.constant.EnumGISObjectType;
import com.elon.model.ShapeFieldInfo;
import com.elon.model.gismodel.GISObjectBase;
import com.elon.model.gismodel.GISPoint;
import com.elon.model.gismodel.GISLine;
import com.elon.model.gismodel.GISMultiPolygon;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

/**
 * Shape文件操作公共类。
 * @author elon
 * @version 2018年6月24日
 */
public class ShapeUtils {

    /**
     * 提取shape文件包含的属性字段名称和类型信息。
     * 
     * @param shpFilePath shape文件路径
     * @return 属性信息
     */
    public static List<ShapeFieldInfo> distillShapeFieldInfo(String shpFilePath) {
        List<ShapeFieldInfo> fieldList = new ArrayList<>();
        ShapefileDataStore dataStroe = buildDataStore(shpFilePath);

        try {
            List<AttributeDescriptor> attrList = dataStroe.getFeatureSource().getSchema()
                    .getAttributeDescriptors();
            for (AttributeDescriptor attr : attrList) {
                ShapeFieldInfo field = new ShapeFieldInfo(attr.getLocalName(), attr.getType().getBinding());
                fieldList.add(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataStroe.dispose();
        }

        return fieldList;
    }
    
    /**
     * 读取GIS图层对象。
     * @param shpFilePath shp文件路径
     * @return 对象列表
     */
    @SuppressWarnings("unchecked")
    public static <T extends GISObjectBase> List<T> readGisObject(String shpFilePath) {
        List<T> gisObjectList = new ArrayList<>();
        List<ShapeFieldInfo> attrFieldList = distillShapeFieldInfo(shpFilePath);
        ShapefileDataStore dataStore = buildDataStore(shpFilePath);

        try {
            String typeName = dataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType, SimpleFeature> fs = dataStore.getFeatureSource(typeName);
            FeatureCollection<SimpleFeatureType, SimpleFeature> fcResult = fs.getFeatures();
            System.out.println("fcResult size:" + fcResult.size());

            FeatureIterator<SimpleFeature> iter = fcResult.features();
            while (iter.hasNext()) {
                SimpleFeature sf = iter.next();
                Collection<Property> property = sf.getProperties();
                Iterator<Property> iterP = property.iterator();
                while (iterP.hasNext()) {
                    Property pro = iterP.next();

                    T t = null;
                    if (pro.getValue() instanceof MultiPolygon) {
                        t = (T) new GISMultiPolygon((MultiPolygon) pro.getValue(), attrFieldList);
                    } else if (pro.getValue() instanceof Point) {
                        t = (T) new GISPoint((Point) pro.getValue(), attrFieldList);
                    } else if (pro.getValue() instanceof MultiLineString) {
                        t = (T) new GISLine((MultiLineString) pro.getValue(), attrFieldList);
                    } else {
                        System.out.print("Invalid gis object type:" + pro.getValue().getClass());
                        continue;
                    }

                    fillGisObjectAttr(t, sf);
                    gisObjectList.add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataStore.dispose();
        }

        return gisObjectList;
    }
    
    /**
     * 获取Shape文件的坐标系信息。
     * 
     * @param shpFilePath shp文件路径
     * @return 坐标系的WKT形式
     */
    public static String getCoordinateSystemWKT(String shpFilePath) {

        ShapefileDataStore dataStore = buildDataStore(shpFilePath);

        try {
            return dataStore.getSchema().getCoordinateReferenceSystem().toWKT();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataStore.dispose();
        }
        return "";
    }

    /**
     * 写GIS对象到Shape文件。
     * @param dataStore shape文件源
     * @param gisObjectList 对象列表
     * @param attrFieldList 属性列表
     * @throws IOException
     */
    public static <T extends GISObjectBase> void writeShapeFile(ShapefileDataStore dataStore, List<T> gisObjectList,
            List<ShapeFieldInfo> attrFieldList) throws IOException {
        if (gisObjectList.isEmpty()) {
            return;
        }
        EnumGISObjectType type = gisObjectList.get(0).getType();

        // 设置图层对象属性
        SimpleFeatureType featureType = buildFeatureType(attrFieldList, type);
        dataStore.createSchema(featureType);
        dataStore.setCharset(Charset.forName("GBK"));

        List<SimpleFeature> featureList = buildFeatureList(gisObjectList, type, featureType);

        // 写数据
        SimpleFeatureSource featureSource = (SimpleFeatureSource) dataStore
                .getFeatureSource(dataStore.getTypeNames()[0]);
        SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
        SimpleFeatureCollection collection = new ListFeatureCollection(featureType, featureList);

        Transaction transaction = new DefaultTransaction("create");
        featureStore.setTransaction(transaction);
        try {
            featureStore.addFeatures(collection);
            transaction.commit();
        } catch (Exception problem) {
            problem.printStackTrace();
            transaction.rollback();
        } finally {
            transaction.close();
        }
    }
    
    /**
     * 构建feature对象列表。
     * 
     * @param gisObjectList GIS对象列表
     * @param type feature类型
     * @param featureType
     * @return feature列表
     */
    private static <T extends GISObjectBase> List<SimpleFeature> buildFeatureList(List<T> gisObjectList,
            EnumGISObjectType type, SimpleFeatureType featureType){
        
        List<SimpleFeature> featureList = new ArrayList<>();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
        for (T gis : gisObjectList) {
            if (type == EnumGISObjectType.POINT) {
                featureBuilder.set("the_geom", ((GISPoint)gis).getPoint());
            }else if (type == EnumGISObjectType.LINE) {
                featureBuilder.set("the_geom", ((GISLine)gis).getLine());
            }else if (type == EnumGISObjectType.POLYGON) {
                featureBuilder.set("the_geom", ((GISMultiPolygon)gis).getPolygon());
            }else {
                System.out.println("Invalid gisobject type.");
                continue;
            }
            
            gis.getAttributeMap().forEach((k, v) -> featureBuilder.set(k, v));
            featureList.add(featureBuilder.buildFeature(null));
        }
        
        return featureList;
    }
    
    /**
     * 构建feature类型信息。
     * 
     * @param attrFieldList 属性字段列表
     * @param type gis对象类型
     * @return feature类型
     */
    private static SimpleFeatureType buildFeatureType(List<ShapeFieldInfo> attrFieldList,
            EnumGISObjectType type) {
        SimpleFeatureTypeBuilder sb = new SimpleFeatureTypeBuilder();
        sb.setCRS(DefaultGeographicCRS.WGS84);

        sb.setName("shape");
        attrFieldList.forEach((f) -> sb.add(f.getFieldName(), f.getFieldType()));
        if (type == EnumGISObjectType.POINT) {
            sb.add("the_geom", Point.class);            
        }else if (type == EnumGISObjectType.LINE) {
            sb.add("the_geom", MultiLineString.class); 
        }else if (type == EnumGISObjectType.POLYGON) {
            sb.add("the_geom", MultiPolygon.class);  
        }else {
            System.out.println("Invalid gisobject type.");
            return null;
        }
        
        return sb.buildFeatureType();
    }
    
    /**
     * 填充GIS对象的属性信息。
     * 
     * @param gisObjectList gis对象列表
     */
    private static <T extends GISObjectBase> void fillGisObjectAttr(T gisObject, SimpleFeature feature) {
        for (ShapeFieldInfo field : gisObject.getAttrFieldList()) {
            Object value = feature.getAttribute(field.getFieldName());
            gisObject.addAttribute(field.getFieldName(), value);
        }

        System.out.println(gisObject.getAttributeMap());
    }
    
    /**
     * 构建ShapeDataStore对象。
     * @param shpFilePath shape文件路径。
     * @return
     */
    public static ShapefileDataStore buildDataStore(String shpFilePath) {
        ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore) factory
                    .createDataStore(new File(shpFilePath).toURI().toURL());
            if (dataStore != null) {
                dataStore.setCharset(Charset.forName("UTF-8"));
            }
            return dataStore;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
