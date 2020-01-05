package cn.itcast.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/*
JedisPool工具类
    加载连接池配置文件，配置连接池参数
    提供获取连接的方法
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool = null;

    // 使用静态代码块初始化Jedis连接池
    static {
        try {
            // 1. 加载连接池配置文件
            Properties properties = new Properties();
            properties.load(JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));

            // 2. 配置连接池参数
            // 创建连接池配置对象
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));   // 最大允许连接数
            config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));     // 最大空闲连接数
            // 配置
            jedisPool = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 获取连接
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
