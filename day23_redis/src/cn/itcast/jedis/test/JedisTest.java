package cn.itcast.jedis.test;

import cn.itcast.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
jedis测试类
 */
public class JedisTest {

    @Test
    public void test1() {
        // 1. 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        // 2. 操作redis数据库
        jedis.set("username", "zhangshan");

        // 3. 关闭连接
        jedis.close();
    }


    /*
    操作字符串类型
     */
    @Test
    public void test2() {
        // 1. 获取连接
        Jedis jedis = new Jedis();  // 如果使用空参构造，则默认值为："localhost", 6379

        // 2. 操作
        // 存储
        jedis.set("username", "zhangshan");

        // 获取
        String username = jedis.get("username");
        System.out.println(username);

        // 可以使用setex()方法存储指定过期时间的key
        jedis.setex("gender", 20, "male");  // 设置key的过期时间为20秒，20秒后就会被删除掉


        // 3. 关闭连接
        jedis.close();
    }



    /*
    操作哈希类型
     */
    @Test
    public void test3() {
        // 1. 获取连接
        Jedis jedis = new Jedis();  // 如果使用空参构造，则默认值为："localhost", 6379

        // 2. 操作
        // 存储
        jedis.hset("user", "name", "coco");
        jedis.hset("user", "age", "18");
        jedis.hset("user", "gender", "female");

        // 获取
        String gender = jedis.hget("user","gender");
        System.out.println(gender);

        // 获取所有的key和value
        Map<String, String> user = jedis.hgetAll("user");
        for (String key : user.keySet()) {
            System.out.println(user.get(key));
        }

        // 3. 关闭连接
        jedis.close();
    }




    /*
    操作列表类型
     */
    @Test
    public void test4() {
        // 1. 获取连接
        Jedis jedis = new Jedis();  // 如果使用空参构造，则默认值为："localhost", 6379

        // 2. 操作
        // 存储
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");

        // 获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        // 删除并返回
        String left_elements = jedis.lpop("mylist");
        System.out.println(left_elements);
        String right_elements = jedis.rpop("mylist");
        System.out.println(right_elements);

        // 3. 关闭连接
        jedis.close();
    }




    /*
    操作集合类型
     */
    @Test
    public void test5() {
        // 1. 获取连接
        Jedis jedis = new Jedis();  // 如果使用空参构造，则默认值为："localhost", 6379

        // 2. 操作
        // 存储
        jedis.sadd("myset", "java", "php", "c++");

        // 获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        // 3. 关闭连接
        jedis.close();
    }




    /*
    操作有序集合类型
     */
    @Test
    public void test6() {
        // 1. 获取连接
        Jedis jedis = new Jedis();  // 如果使用空参构造，则默认值为："localhost", 6379

        // 2. 操作
        // 存储
        jedis.zadd("mysortedset", 10, "coco");
        jedis.zadd("mysortedset", 5, "tutu");
        jedis.zadd("mysortedset", 7, "mumu");

        // 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        // 3. 关闭连接
        jedis.close();
    }




    /*
    jedis连接池的使用
     */
    @Test
    public void test7() {
        // 0. 创建连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 配置连接池
        config.setMaxTotal(50);   // 最大允许连接数
        config.setMaxIdle(10);  // 最大空闲连接数


        // 1. 创建连接池对象
        //JedisPool jedisPool = new JedisPool();    // 可以使用空参构造，默认是连接本地的redis，端口为6379
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        // 2. 获取连接
        Jedis jedis = jedisPool.getResource();

        // 3. 操作
        jedis.set("username", "lianflower");
        String username = jedis.get("username");
        System.out.println(username);

        // 4. 将连接归还到连接池钟
        jedis.close();
    }




    /*
    jedis连接池工具类的使用
     */
    @Test
    public void test8() {

        // 2. 使用连接池工具类获取连接
        Jedis jedis = JedisPoolUtils.getJedis();

        // 3. 操作
        jedis.set("username", "lianflower");
        String username = jedis.get("username");
        System.out.println(username);

        // 4. 将连接归还到连接池钟
        jedis.close();
    }

}
