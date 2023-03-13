package com.my.github.majiang.community.mytestcommunity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRspDTO {
    String login;
    Long id;
    String name;

    @Override
    public String toString() {
        return "UserRspDTO{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
