package cn.itcast.reflect;

import cn.itcast.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
    Class对象的常用功能：
		1. 获取成员变量们
			* Field[] getFields() ：获取所有public修饰的成员变量
			* Field getField(String name)   获取指定名称的 public修饰的成员变量

			* Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
			* Field getDeclaredField(String name)   获取指定名称的成员变量，不考虑修饰符
		2. 获取构造方法们
			* Constructor<?>[] getConstructors()
			* Constructor<T> getConstructor(类<?>... parameterTypes)

			* Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
			* Constructor<?>[] getDeclaredConstructors()
		3. 获取成员方法们：
			* Method[] getMethods()
			* Method getMethod(String name, 类<?>... parameterTypes)

			* Method[] getDeclaredMethods()
			* Method getDeclaredMethod(String name, 类<?>... parameterTypes)

		4. 获取全类名
			* String getName()
     */
public class ReflectDemo02 {
    public static void main(String[] args) throws Exception {
        // 0. 获取Person的Class对象
        Class personClass = Person.class;

        // 1. 获取成员变量们
        // 获取所有public修饰的成员变量
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // 获取指定名称的 public修饰的成员变量
        Field field = personClass.getField("a");
        System.out.println(field);
        // 获取成员变量的值
        Person person = new Person();
        Object value = field.get(person);
        System.out.println(value);
        // 设置成员变量的值
        field.set(person, "张三");
        System.out.println(person);
        // 获取所有的成员变量，不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        // 获取指定名称的成员变量，不考虑修饰符
        Field d = personClass.getDeclaredField("d");
        System.out.println(d);
        // 获取成员变量的值
        // 因为字段d是私有的，所以在获取前，需要忽略权限修饰符的安全检查
        d.setAccessible(true);  // 也称为暴力反射
        Object value2 = d.get(person);
        System.out.println(value2);
        // 设置成员变量的值
        d.set(person, "钟跃民");
        System.out.println(person);


        // 2. 获取构造方法们
        // 获取指定名称的构造方法
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        Object coco = constructor.newInstance("coco", 19);
        System.out.println(coco);

        // 获取无参构造方法
        Constructor constructor1 = personClass.getConstructor();
        Object o = constructor1.newInstance();
        System.out.println(o);


        // 3. 获取成员方法们
        // 获取指定名称的成员方法
        Method method1 = personClass.getMethod("eat");  // 获取无参数的成员方法
        Person person1 = new Person();
        method1.invoke(person1);

        Method method2 = personClass.getMethod("eat", String.class);  // 获取有参数的成员方法
        method2.invoke(person1, "水果");

        // 获取所有public修饰的成员方法（包括继承父类的方法）
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            String name = method.getName(); // 获取方法名称
            System.out.println(name);
        }


        // 4. 获取类名
        String className = personClass.getName();
        System.out.println(className);


    }
}

