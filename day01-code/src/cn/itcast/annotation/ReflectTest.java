package cn.itcast.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
使用反射，自定义框架类
功能：在不改变任何代码的前提下，可以创建任意类的对象，以及执行任意对象的方法
步骤：
    1. 解析注解，获取类名和方法名
    2. 使用反射将类文件加载进内存
    3. 创建对象
    4. 执行方法
 */
@Pro(className = "cn.itcast.annotation.Demo2", methodName = "show2")
public class ReflectTest {
    public static void main(String[] args) throws Exception {

        /*
        1. 解析注解
         */
        // 获取当前类的Class对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        // 获取注解@pro的实现类的对象
        // annotation是实现了注解@Pro的类的对象；注解也是接口
        Pro annotation = reflectTestClass.getAnnotation(Pro.class);
        // 调用注解中的抽象方法（属性），返回属性值
        String className = annotation.className();
        String methodName = annotation.methodName();
        System.out.println(className);
        System.out.println(methodName);


        /*
        2. 使用反射将类文件加载进内存
         */
        Class cls = Class.forName(className);

        /*
        3. 创建对象
         */
        // 获取构造方法
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();

        /*
        4. 执行方法
         */
        // 获取成员方法
        Method method = cls.getMethod(methodName);
        method.invoke(obj);


    }
}
