package com.my.github.majiang.community.mytestcommunity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenRspDTO {
    String access_token;
    String token_type;
    String scope;
    String error_code;
}
