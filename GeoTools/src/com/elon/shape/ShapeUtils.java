package com.elon.shape;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.opengis.feature.type.AttributeDescriptor;

import com.elon.model.ShapeFieldInfo;

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
            List<AttributeDescriptor> attrList = dataStroe.getFeatureSource().getSchema().getAttributeDescriptors();
            for (AttributeDescriptor attr : attrList) {
                ShapeFieldInfo field = new ShapeFieldInfo();
                field.setFieldName(attr.getLocalName());
                field.setFieldType(attr.getType().getBinding());
                fieldList.add(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dataStroe.dispose();
        }

        System.out.println("fieldList:" + fieldList);
        return fieldList;
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
            return dataStore;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
