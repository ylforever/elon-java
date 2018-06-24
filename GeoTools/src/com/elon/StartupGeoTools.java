package com.elon;

import java.io.File;

import com.elon.shape.ShapeUtils;

public class StartupGeoTools {
	public static void main(String[] args) {
	    
	    String workSpacePath = System.getProperty("user.dir");
	    String shpFilePath = workSpacePath + File.separator + "shape/ne_50m_admin_0_countries/ne_50m_admin_0_countries.shp";
	    ShapeUtils.distillShapeFieldInfo(shpFilePath);
		System.out.println("Start GeoTools success!");
	}
}
