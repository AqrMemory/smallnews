package com.znstart.controller;

import com.znstart.pojo.User;
import com.znstart.service.UserService;
import com.znstart.util.JwtHelper;
import com.znstart.util.Result;
import com.znstart.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author znhstart
 * @Create 2024/1/5 0005 8:23
 * @Version 1.0
 */
@RestController  //接受jison数据
@RequestMapping("user")  //设置跟路径
@CrossOrigin  //跨域
public class UserController {
        @Autowired
       private JwtHelper jwtHelper;
    /**
     * 登录需求
     * 地址: /user/login
     * 方式: post
     * 参数:
     *    {
     *     "username":"zhangsan", //用户名
     *     "userPwd":"123456"     //明文密码
     *    }
     *    json数据用实体类接受
     * 返回:
     *   {
     *    "code":"200",         // 成功状态码
     *    "message":"success"   // 成功状态描述
     *    "data":{
     *         "token":"... ..." // 用户id的token
     *     }
     *  }
     *
     * 大概流程:
     *    1. 账号进行数据库查询 返回用户对象
     *    2. 对比用户密码(md5加密)
     *    3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     *    4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    //只负责 接收和返回结果，要注入业务层
    @Autowired
    private UserService userService;

    @PostMapping( "login")
    public Result login(@RequestBody User user){ //接json数据 加@RequestBody

         Result result= userService.login(user); //业务层返回值
        System.out.println( "result=" + result);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo (@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username) {
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {

        Result result = userService.regist(user);
        return result;

    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
    }

