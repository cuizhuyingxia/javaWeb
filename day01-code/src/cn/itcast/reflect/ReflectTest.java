package cn.itcast.reflect;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/*
使用反射，自定义框架类
功能：在不改变任何代码的前提下，可以创建任意类的对象，以及执行任意对象的方法
步骤：
    1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
    2. 加载配置文件
    3. 使用反射将类文件加载进内存
    4. 创建对象
    5. 执行方法
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {

        /*
        2. 加载配置文件
         */
        // 获取当前类的类加载器
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        // 调用当前类的类加载器的getResourceAsStream()方法获取src目录下的配置文件pro.properties的字节流
        InputStream resourceAsStream = classLoader.getResourceAsStream("pro.properties");
        // 调用Properties对象的load()方法加载配置文件
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        // 获取配置文件中的数据
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        /*
        3. 使用反射将类文件加载进内存
         */
        Class cls = Class.forName(className);

        /*
        4. 创建对象
         */
        // 获取构造方法
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();

        /*
        5. 执行方法
         */
        // 获取成员方法
        Method method = cls.getMethod(methodName);
        method.invoke(obj);

    }
}
