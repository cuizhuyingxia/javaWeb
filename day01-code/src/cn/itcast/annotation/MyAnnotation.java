package cn.itcast.annotation;

public @interface MyAnnotation {
    /*
    注解中的属性：是一些抽象的方法
    返回值的类型有：基本数据类型、String类型、枚举类型、注解类型、和以上四种类型的数组类型
    在使用注解时，需要给属性赋值；格式：属性名 = 值   不过如果定义属性时，已经指定了默认值，则使用注解时可以不用再重新赋值
    如果只需要给注解中的value属性赋值，那么属性名称value可以不写，直接写值就行，但是，如果要给注解中的多个属性赋值，则属性名称value还是要写的
    在给注解中的属性赋值时，如果只给value属性赋值，则属性名称value可以不写，直接写值就行
    在给返回值是数组类型的属性赋值时，值需要使用{}包裹；如果值只有一个，那么{}可以省略不写
     */

    int value();
    int show1();
    String show2() default "钟跃民";   // 指定默认值
    Person p1();
    MyVnnotation2 anno2();
    String[] strs();



}
