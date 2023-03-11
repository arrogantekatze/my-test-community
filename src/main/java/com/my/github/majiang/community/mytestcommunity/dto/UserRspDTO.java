package com.my.github.majiang.community.mytestcommunity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class UserRspDTO {
    String login;
    long id;
    String name;
}
