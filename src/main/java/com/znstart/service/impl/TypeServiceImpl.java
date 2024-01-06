package com.znstart.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znstart.mapper.TypeMapper;
import com.znstart.pojo.Type;
import com.znstart.pojo.vo.PortalVo;
import com.znstart.service.TypeService;
import com.znstart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Mr.H
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-01-05 06:47:10
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService {
    @Autowired
    private  TypeMapper typeMapper;
    /*查询所有类别数据
    * */

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }




}




