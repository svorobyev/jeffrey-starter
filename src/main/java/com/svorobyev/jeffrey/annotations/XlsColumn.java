package com.svorobyev.jeffrey.annotations;

import com.svorobyev.jeffrey.processor.XlsDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface XlsColumn {

    String title() default "DEFAULT_TITLE";
    XlsDataType type() default XlsDataType.TEXT;
    String hqlQuery() default "";

}
