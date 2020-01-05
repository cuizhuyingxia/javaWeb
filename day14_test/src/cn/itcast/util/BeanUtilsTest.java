package cn.itcast.util;

import cn.itcast.domain.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

    /*
    BeanUtils工具类：用户封装JavaBean

    1. 什么是JavaBean
        JavaBean指的是标准的Java类
            要求：
                (1) 类必须被public修饰
                (2) 必须提供空参构造方法
                (3) 成员变量必须使用private修饰
                (4) 提供public修饰的setter和getter方法

    2. 特点
        成员变量和属性不是一回事
        例如：
            private String gender;

            public String getSex() {
                return gender;
            }

            public void setSex(String gender) {
                this.gender = gender;
            }

            成员变量是gender
            而属性是getSex，去掉get，然后再转换为小写，即sex
                或setSex，去掉set，然后再转换为小写，即sex


    3. 方法
        setProperty(Object bean, String name, Object value)     设置属性值
        getProperty(Object bean, String name)                   获取属性值
        populate(Object bean, Map properties)                   将map集合的键值对信息封装到JavaBean对象中*/

public class BeanUtilsTest {

    @Test
    public void test1() {
        Person person = new Person();
        try {
            BeanUtils.setProperty(person, "gender", "male");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(person);   // Person{name='null', gender='null'}
        // 可以看到gender并没有被赋值，因为gender是成员变量，而BeanUtils的setProperty方法只能给属性赋值
    }

    @Test
    public void test2() {
        Person person = new Person();
        try {
            BeanUtils.setProperty(person, "sex", "male");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(person);   // Person{name='null', gender='male'}
    }
}
