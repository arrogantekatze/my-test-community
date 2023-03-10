package com.my.github.majiang.community.mytestcommunity.controller;

import com.alibaba.fastjson.JSON;
import com.my.github.majiang.community.mytestcommunity.dto.AccessTokenDTO;
import com.my.github.majiang.community.mytestcommunity.dto.TokenRspDTO;
import com.my.github.majiang.community.mytestcommunity.util.MyHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    MyHttpUtils utils;

    // 用于第三方应用防止CSRF攻击
//    String uuid = UUID.randomUUID().toString().replaceAll("-", "");

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        try {
            System.out.println(code);
            System.out.println(state);
            AccessTokenDTO accessToken = new AccessTokenDTO()
                    .setClient_id("361cadf7b86ca3a2361c")
                    .setClient_secret("22232a5a151248caefb9d7ab6f07b517ddb5e147")
                    .setRedirect_uri("http://localhost:8887/callback")
                    .setCode(code);
            String rsp = utils.post("https://github.com/login/oauth/access_token",
                    JSON.toJSONString(accessToken));
            System.out.println(rsp);
//            TokenRspDTO tokenRsp = JSON.parseObject(rsp, TokenRspDTO.class);
            String[] params = rsp.split("&");
            String token = params[0].replace("access_token=","");
            System.out.println(token);
            String userRsp = utils.get("https://api.github.com/user",
                    "{}","Authorization","token "+token);
            System.out.println(userRsp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
