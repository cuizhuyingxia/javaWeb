package cn.itcast.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})  // 表示注解能够作用的位置
@Retention(RetentionPolicy.RUNTIME) // 表示注解被保留的阶段
@Documented // 表示注解会被抽取到api文档中
@Inherited  // 表示注解会被子类继承
public @interface MyAnnotation3 {
}
