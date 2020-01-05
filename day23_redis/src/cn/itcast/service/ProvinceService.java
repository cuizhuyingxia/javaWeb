package cn.itcast.service;

import cn.itcast.domain.Province;

import java.util.List;

public interface ProvinceService {
    /**
     * 查询省份
     * @return
     */
    List<Province> findAll();

    /**
     * 使用redis缓存查询省份
     */
    String findAllJson();

}
