package cn.itcast.junit;

/*
计算器类
 */
public class Calculator {

    public int add(int a, int b) {
        int num = 3 / 0;
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }
}
