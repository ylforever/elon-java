package com.elon.springbootdemo.manager.invalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字段长度校验实现类。
 * 
 * @author elon
 * @version 2018年9月19日
 */
public class FieldLengthInvalidatorImpl implements ConstraintValidator<FieldLengthInvalidator, String> {

    private int maxLength = 0;
    
    @Override
    public void initialize(FieldLengthInvalidator invalidator) {
        maxLength = invalidator.maxLength();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {

        if (fieldValue.length() > maxLength) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("对象属性长度超过限制。").addConstraintViolation();
            
            // 校验失败返回false。返回true上游收集不到错误信息。
            return false;
        }

        return true;
    }
}
