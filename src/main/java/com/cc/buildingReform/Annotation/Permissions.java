package com.cc.buildingReform.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permissions {
	int level() default 0;

	String target() default "";
	boolean isJSON() default false;
	String url() default "";
}