package com.my.github.majiang.community.mytestcommunity.controller;

import com.alibaba.fastjson.JSON;
import com.my.github.majiang.community.mytestcommunity.dto.AccessTokenDTO;
import com.my.github.majiang.community.mytestcommunity.dto.TokenRspDTO;
import com.my.github.majiang.community.mytestcommunity.dto.UserRspDTO;
import com.my.github.majiang.community.mytestcommunity.util.MyHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    MyHttpUtils utils;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.clinet.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    // 用于第三方应用防止CSRF攻击
//    String uuid = UUID.randomUUID().toString().replaceAll("-", "");

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        try {
            AccessTokenDTO accessToken = new AccessTokenDTO()
                    .setClient_id(clientId)
                    .setClient_secret(clientSecret)
                    .setRedirect_uri(redirectUri)
                    .setCode(code);
            String rspToken = utils.post("https://github.com/login/oauth/access_token",
                    JSON.toJSONString(accessToken));
            System.out.println(rspToken);
            TokenRspDTO tokenRsp = JSON.parseObject(rspToken, TokenRspDTO.class);
//            String[] params = rsp.split("&");
//            String token = params[0].replace("access_token=","");
            String token = tokenRsp.getAccess_token();
            String rspUser = utils.get("https://api.github.com/user",
                    "{}","Authorization","token "+token);
            UserRspDTO userRsp = JSON.parseObject(rspUser, UserRspDTO.class);
            System.out.println(userRsp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
