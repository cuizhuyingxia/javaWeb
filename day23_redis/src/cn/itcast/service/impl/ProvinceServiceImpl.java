package cn.itcast.service.impl;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.impl.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.jedis.util.JedisPoolUtils;
import cn.itcast.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    // 创建ProvinceDao对象
    ProvinceDao provinceDao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public String findAllJson() {
        // 1. 先从redis中查询数据
        // 1.1 使用连接池工具类获取连接
        Jedis jedis = JedisPoolUtils.getJedis();
        // 1.2 获取省份信息
        String province_json = jedis.get("province");

        // 2. 判断redis中是否有数据
        if (province_json == null || province_json.length() == 0) {
            System.out.println("redis中没有数据，查询数据库....");
            // 2.1 从数据库中查询数据
            List<Province> list = provinceDao.findAll();
            try {
                // 2.2 然后将数据序列化（转换）为JSON字符串
                ObjectMapper objectMapper = new ObjectMapper();
                province_json = objectMapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 2.3 再将JSON字符串存入到redis中
            jedis.set("province", province_json);

        } else {
            System.out.println("redis中有数据，查询redis....");
        }


        return province_json;
    }
}
