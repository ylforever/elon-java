package com.elon.springbootdemo.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 有效性校验管理类。对外提供统一的校验调用接口。
 * @author elon
 * @version 2018年9月19日
 */
public class InvalidatorMgr {
    private InvalidatorMgr() {
        
    }
    
    /**
     * 获取单例对象。
     * 
     * @return 单例对象
     */
    public static InvalidatorMgr instance() {
        return InvalidatorMgrBuilder.instance;
    }
    
    /**
     * 校验模型所有属性的有效性。
     * 
     * @param model 待校验模型
     * @return 错误信息列表
     */
    public <T> List<String> validate(T model) {
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> resultSet = validator.validate(model);
        
        List<String> messageList = new ArrayList<>();
        resultSet.forEach((r)->messageList.add(r.getMessage()));
        
        return messageList;
    }
    
    /**
     * 单例构建器。
     * @author elon
     * @version 2018年9月19日
     */
    private static class InvalidatorMgrBuilder{
        private static InvalidatorMgr instance = new InvalidatorMgr();
    }
}
