package com.elon.model.gismodel;

import java.io.Serializable;

import org.opengis.feature.simple.SimpleFeature;

import com.elon.constant.EnumGISObjectType;

/**
 * GIS对象基类定义。
 * 
 * @author elon
 * @version 2018年6月26日
 */
public class GISObjectBase implements Serializable {
   
    private static final long serialVersionUID = -6147262367078689317L;
    
    private final EnumGISObjectType type;
    
    private SimpleFeature simpleFeature = null;
    
    protected GISObjectBase(EnumGISObjectType type, SimpleFeature simpleFeature){
        this.type = type;
        this.simpleFeature = simpleFeature;
    }

    public EnumGISObjectType getType() {
        return type;
    }

    public SimpleFeature getSimpleFeature() {
        return simpleFeature;
    }
}
