package com.znstart.service;

import com.znstart.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znstart.util.Result;

/**
* @author Mr.H
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-01-05 06:47:10
*/
public interface UserService extends IService<User> {
/*登录业务
* */
    Result login(User user) ;

    /*
    * 根据token获取用户信息
    * */
    Result getUserInfo(String token);


    Result checkUserName(String username);

    //注册业务
    Result regist(User user);
}
