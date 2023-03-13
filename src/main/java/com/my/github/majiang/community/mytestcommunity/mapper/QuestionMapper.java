package com.my.github.majiang.community.mytestcommunity.mapper;

import com.my.github.majiang.community.mytestcommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("inset into question(title,description,create_time,update_time,creator,comment_count,view_count,tag) " +
            "values (#{title},#{description},#{createTime},#{updateTime},#{creator},#{commentCount},#{viewCount},#{tag}")
    void insert(Question question);

}
