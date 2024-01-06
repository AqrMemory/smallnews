package com.znstart.service;

import com.znstart.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znstart.pojo.vo.PortalVo;
import com.znstart.util.Result;

/**
* @author Mr.H
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-01-05 06:47:10
*/
public interface TypeService extends IService<Type> {
    /*
    * 查询所有类别数据
    * */
    Result findAllTypes();



}
