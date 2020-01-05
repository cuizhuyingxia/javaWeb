package cn.itcast.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectTest2 {
    public static void main(String[] args) throws Exception {
        see();
    }
    @Pro(className = "cn.itcast.annotation.Demo1", methodName = "show1")
    public static void see() throws Exception {
        /*
        1. 解析注解
         */
        // 获取当前类的Class对象
        Class<ReflectTest2> reflectTest2Class = ReflectTest2.class;
        // 因为注解是定义在方法上面的，所以需要先获取到方法对象，然后使用方法对象调用getAnnotation()方法
        Method see = reflectTest2Class.getMethod("see");
        // 获取注解@pro的实现类的对象
        Pro annotation = see.getAnnotation(Pro.class);
        // 调用注解中的抽象方法（属性），返回属性值
        String className = annotation.className();
        String methodName = annotation.methodName();


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
