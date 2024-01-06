package com.znstart.service;

import com.znstart.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znstart.pojo.vo.PortalVo;
import com.znstart.util.Result;

/**
* @author Mr.H
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-01-05 06:47:10
*/
public interface HeadlineService extends IService<Headline> {

    /**首页数据查询
     *
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**根据id查询详情
     *
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条的方法
     * @param headline
     * @param token
     * @return
     */
    Result pulish(Headline headline, String token);

    /**
     * 修改头条
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}
