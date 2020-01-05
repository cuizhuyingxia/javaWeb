package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Proxy_test {
    public static void main(String[] args) {

        // 创建真实对象
        Lenovo lenovo = new Lenovo();

        // 使用动态代理增强真实对象
        /*
        使用Proxy类调用newProxyInstance方法获取一个代理对象

        1. 需要传入三个参数：
            真实对象的类加载器
            真实对象实现的接口
            InvocationHandler接口的实现类（可以使用匿名内部类来完成）

        2. 实现InvocationHandler接口的抽象方法invoke

        因为代理对象和真实对象的类加载器以及实现的接口都是一样的，所以代理对象可以调用真实对象的方法，
            只不过代理对象在调用真实对象的方法时，真实对象的方法并没有被执行，真正执行的是invoke方法，
                代理对象在调用真实对象的方法时，会将调用的方法封装为对象传给invoke方法，会将调用方法时传递的参数封装为数组传给invoke方法
                    然后invoke方法会被自动执行（为什么invoke方法会自动被执行呢？暂时还不知道）
                        所以我们可以在invoke方法中再使用真实对象调用真实对象的方法，不过我们可以对真实对象的方法的参数以及返回值做一些修改，
                            以及在调用真实对象的方法前后增加一些其它操作，以此来增强真实对象的功能
         */
        SaleComputer proxyLenovo = (SaleComputer) Proxy.newProxyInstance(Lenovo.class.getClassLoader(), Lenovo.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*
                Object proxy    代理对象
                Method method   代理对象在调用真实对象的方法时，会将调用的方法封装为对象传给invoke方法
                Object[] args   代理对象在调用真实对象的方法时，会将调用方法时传递的参数封装为数组传给invoke方法
                 */

                // 1. 增强参数，对参数做一些修改
                double money = (double) args[0];
                money = money * 0.85;

                // 2. 增强方法体，在执行方法体前添加一些操作
                System.out.println("专车接你");
                // 使用真实对象调用真实对象的方法
                String str = (String) method.invoke(lenovo, money); // 注意：这里的invoke()并不是实现InvocationHandler接口的抽象方法invoke
                                                                    // （反射）而是方法对象中的invoke方法，每个方法对象都会有invoke方法，用于通过传入类对象和参数来执行该方法
                // 在执行方法体后添加一些操作
                System.out.println("送货上门");

                // 3. 增强返回值，对返回值做一些修改
                return str + "_鼠标垫";
            }
        });

        // 使用代理对象调用真实对象的方法（真正执行的方法为invoke方法）
        String computer = proxyLenovo.sale(9000);
        System.out.println(computer);

    }

}
