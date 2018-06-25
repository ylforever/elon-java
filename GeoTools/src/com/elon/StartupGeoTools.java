package com.elon;

import java.io.File;
import java.util.List;

import com.elon.model.ShapeFieldInfo;
import com.elon.model.gismodel.GisMultiPolygon;
import com.elon.shape.ShapeUtils;

public class StartupGeoTools {
	public static void main(String[] args) {
	    
	    String workSpacePath = System.getProperty("user.dir");
	    String shpFilePath = workSpacePath + File.separator + "shape/ne_50m_admin_0_countries/ne_50m_admin_0_countries.shp";
	    
	    List<ShapeFieldInfo> fieldList = ShapeUtils.distillShapeFieldInfo(shpFilePath);
        System.out.println("fieldList:" + fieldList);

	    List<GisMultiPolygon> gisObjectList = ShapeUtils.readGisObject(shpFilePath);
	    System.out.println("gisObjectList size:" + gisObjectList.size());
	    
	    System.out.println("Start GeoTools success!");
	}
}
