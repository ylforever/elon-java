package com.elon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.shapefile.ShapefileDataStore;

import com.elon.model.ShapeFieldInfo;
import com.elon.model.gismodel.GISPoint;
import com.elon.model.gismodel.GisMultiPolygon;
import com.elon.shape.ShapeUtils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

public class StartupGeoTools {
	public static void main(String[] args) throws IOException {
	    
	    String workSpacePath = System.getProperty("user.dir");
	    String shpFilePath = workSpacePath + File.separator + "shape/ne_50m_admin_0_countries/ne_50m_admin_0_countries.shp";
	    
	    List<ShapeFieldInfo> fieldList = ShapeUtils.distillShapeFieldInfo(shpFilePath);
        System.out.println("fieldList:" + fieldList);

	    List<GisMultiPolygon> gisObjectList = ShapeUtils.readGisObject(shpFilePath);
	    System.out.println("gisObjectList size:" + gisObjectList.size());
	    
	    String wkt = ShapeUtils.getCoordinateSystemWKT(shpFilePath);
	    System.out.println("WKT:" + wkt);
	    
        List<ShapeFieldInfo> attrFieldList = new ArrayList<>();
        attrFieldList.add(new ShapeFieldInfo("id", Integer.class));
        attrFieldList.add(new ShapeFieldInfo("name", String.class));
        
        GeometryFactory factory = new GeometryFactory();
        List<GISPoint> pointList = new ArrayList<>();
        
        GISPoint point1 = new GISPoint(factory.createPoint(new Coordinate(20.11, 35.22)), attrFieldList);
        point1.getAttributeMap().put("id", 6);
        point1.getAttributeMap().put("name", "666");
        pointList.add(point1);
        
        GISPoint point2 = new GISPoint(factory.createPoint(new Coordinate(50.121, 45.2222)), attrFieldList);
        point2.getAttributeMap().put("id", 8);
        point2.getAttributeMap().put("name", "888");
        pointList.add(point2);
        
        String shpFilePath2 = workSpacePath + File.separator + "shape/student/student.shp";
        ShapefileDataStore dataStore = ShapeUtils.buildDataStore(shpFilePath2);
	    ShapeUtils.writePoint2ShapeFile(dataStore, pointList, attrFieldList);
	    dataStore.dispose();
	    
	    System.out.println("Start GeoTools success!");
	}
}
