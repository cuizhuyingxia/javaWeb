package cn.itcast.reflect;

import cn.itcast.domain.Person;
import cn.itcast.domain.Student;

/*
获取Class对象的三种方式：
1. Class.forName("class文件全名") 将字节码文件加载进内存，返回Class对象
2. 类名.class  通过类名的class属性获取Class对象
3. 3. 对象.getClass()   调用对象的getClass()方法获取Class对象
 */
public class ReflectDemo01 {
    public static void main(String[] args) throws Exception {
        // 1. Class.forName("class文件全名") 将字节码文件加载进内存，返回Class对象
        Class class1 = Class.forName("cn.itcast.domain.Student");
        System.out.println(class1);

        // 2. 类名.class  通过类名的class属性获取Class对象
        Class class2 = Student.class;
        System.out.println(class2);

        // 3. 对象.getClass()   调用对象的getClass()方法获取Class对象
        Student student = new Student("coco", 18);
        Class class3 = student.getClass();
        System.out.println(class3);


    }
}
