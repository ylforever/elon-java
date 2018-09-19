package com.elon.springbootdemo.manager.invalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 属性字段长度校验注解定义。
 * 
 * @author elon
 * @version 2018年9月19日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldLengthInvalidatorImpl.class)
@Documented
public @interface FieldLengthInvalidator {

    // 字段支持的最大长度(字符数)
    int maxLength() default 50;

    // 校验失败后返回的错误信息
    String message() default "";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
