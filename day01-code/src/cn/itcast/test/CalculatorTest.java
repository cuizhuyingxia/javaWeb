package cn.itcast.test;

import cn.itcast.junit.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    /*
    @Before注解修饰的方法会在测试方法之前执行
    @After注解修饰的方法会在测试方法之后执行
    注意：无论测试方法是否会出现异常，@Before注解和@After注解修饰的方法都会被执行
     */
    @Before
    public void init() {
        System.out.println("init...");
    }

    @After
    public void close() {
        System.out.println("close..");
    }


    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        // 断言   参数1为期望的结果   参数2为执行的结果
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSub() {
        Calculator calculator = new Calculator();
        int result = calculator.sub(1, 2);
        // 断言   参数1为期望的结果   参数2为执行的结果
        Assert.assertEquals(-1, result);
    }
}
