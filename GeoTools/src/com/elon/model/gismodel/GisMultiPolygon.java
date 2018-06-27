package com.elon.model.gismodel;

import java.util.List;

import org.opengis.feature.simple.SimpleFeature;

import com.elon.constant.EnumGISObjectType;
import com.elon.model.ShapeFieldInfo;
import com.vividsolutions.jts.geom.MultiPolygon;

/**
 * 多边形模型。
 * 
 * @author elon
 * @version 2018年6月26日
 */
public class GisMultiPolygon extends GISObjectBase {

    private static final long serialVersionUID = -5705724544971923893L;

    /**
     * 多边形的geomtry模型。
     */
    private MultiPolygon polygon = null;;
    
    public GisMultiPolygon(MultiPolygon polygon, SimpleFeature simpleFeature,
            List<ShapeFieldInfo> attrFieldList) {
        super(EnumGISObjectType.POLYGON, simpleFeature, attrFieldList);
        this.polygon = polygon;
    }

    public MultiPolygon getPolygon() {
        return polygon;
    }

    public void setPolygon(MultiPolygon polygon) {
        this.polygon = polygon;
    }
}
