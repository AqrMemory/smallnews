package com.znstart.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znstart.mapper.HeadlineMapper;
import com.znstart.pojo.Headline;
import com.znstart.pojo.vo.PortalVo;
import com.znstart.service.HeadlineService;
import com.znstart.util.JwtHelper;
import com.znstart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Mr.H
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-01-05 06:47:10
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService {

        @Autowired
        private HeadlineMapper headlineMapper;

        @Autowired
        private JwtHelper jwtHelper;

    /**
     * 首页数据查询
     *  1.分页数据查询
     *  2.分页数据拼接到result
     *      查询需自定义语句，自定义mapper的方法
     *      返回结果用lIstanbul<map>
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {

        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());//当前页数，页容量

        headlineMapper.selectMypage(page, portalVo);

        List<Map> records = page.getRecords();
        Map data = new HashMap();
        data.put("pageData", records);
        data.put("pageNum", page.getCurrent()); //页码数
        data.put("pageSize", page.getSize());   //页大小
        data.put("totalPage", page.getPages()); //总页数
        data.put("totalSize ", page.getTotal());  //总记录数

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo", data);

        return Result.ok(pageInfo);
    }
    /**
     * 根据id查询详情
     *
     *
     *   2、查询对应的数据即可 【多表，头条和用户表，方法需要自定义 返回map即可】
     *   1、修改阅读量 + 1  【version乐观锁，当前数据对应的版本】
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data = headlineMapper.queryDetailMap(hid);
        Map headlineMap = new HashMap();
        headlineMap.put("headline", data);

        //修改阅读量
        Headline headline = new Headline();
        headline.setHid((Integer) data.get("hid")); //setHid 主键
        headline.setVersion((Integer) data.get("version"));

        //阅读量 +1
        headline.setPageViews((Integer) data.get("pageview") + 1);
        headlineMapper.updateById(headline);
        return Result.ok(headlineMap);
    }

    @Override
    public Result pulish(Headline headline, String token) {

        //token查询用户id
         int userId = jwtHelper.getUserId(token).intValue();
         //数据装配
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline); //调用方法插入

        return Result.ok(null);
    }

    /**
     * 修改头条数据
     * hid 查询数据的最新version
     * 修改数据的修改时间为当前节点
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline) {

        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version); //乐观锁
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }


}




