package com.my.github.majiang.community.mytestcommunity.mapper;

import com.my.github.majiang.community.mytestcommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,create_time,update_time) " +
            "values(#{name},#{accountId},#{token},#{createTime},#{updateTime})")
    void insert(User user);

}
