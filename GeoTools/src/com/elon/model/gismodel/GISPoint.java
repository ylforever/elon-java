package com.elon.model.gismodel;

import org.opengis.feature.simple.SimpleFeature;

import com.elon.constant.EnumGISObjectType;
import com.vividsolutions.jts.geom.Point;

/**
 * 点对象模型
 * @author elon
 * @version 2018年6月26日
 */
public class GISPoint extends GISObjectBase {

    private static final long serialVersionUID = 851468237977190995L;
    
    /**
     * 点geometry对象
     */
    private Point point = null;

    public GISPoint(Point point, SimpleFeature simpleFeature) {
        super(EnumGISObjectType.POINT, simpleFeature);
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
