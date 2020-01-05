package cn.itcast.test;

public class test {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "world";

        // 创建StringBuilder对象，用来拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(str2);
        System.out.println(stringBuilder);              // helloworld

        // 查看类型
        System.out.println(stringBuilder.getClass());   // class java.lang.StringBuilder

        // 再转换为字符串类型
        String string = stringBuilder.toString();
        System.out.println(string);                     // helloworld
        System.out.println(string.getClass());          // class java.lang.String

    }
}
