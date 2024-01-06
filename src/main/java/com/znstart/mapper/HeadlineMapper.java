package com.znstart.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.znstart.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.znstart.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Mr.H
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-01-05 06:47:10
* @Entity con.znstart.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    //自定义分页查询方法
    IPage<Map> selectMypage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




