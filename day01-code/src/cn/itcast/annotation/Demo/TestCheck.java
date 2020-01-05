package cn.itcast.annotation.Demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;

/*
简单的测试框架：
判断加了@Check注解的方法，是否有异常
 */
public class TestCheck {
    public static void main(String[] args) throws Exception {
        // 1. 创建计算器对象
        Calculator calculator = new Calculator();
        // 2. 获取Calculator的Class对象
        Class cls = calculator.getClass();
        // 3. 获取所有的方法
        Method[] methods = cls.getMethods();

        // 创建输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("bug.txt"));
        // 创建变量，记录异常出现的次数
        int num = 0;

        // 4. 执行有@Check注解的方法
        for (Method method : methods) {
            // 判断方法上是否为@Check注解
            if (method.isAnnotationPresent(Check.class)) {
                // 5. 捕获异常
                try {
                    method.invoke(calculator);
                } catch (Exception e) {
                    // 6. 记录异常信息
                    num ++;
                    bufferedWriter.write(method.getName() + "出异常了");
                    bufferedWriter.newLine();
                    bufferedWriter.write("异常的名称是：" + e.getCause().getClass().getName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("异常的原因是：" + e.getCause().getMessage());
                    bufferedWriter.newLine();
                    bufferedWriter.write("-----------------");
                    bufferedWriter.newLine();
                }
            }
        }
        bufferedWriter.write("本次测试以共出现" + num + "次异常");

        // 刷新流，强制将缓冲区中的数据立刻保存到指定文件中
        bufferedWriter.flush();
        // 关闭资源
        bufferedWriter.close();
    }
}
















