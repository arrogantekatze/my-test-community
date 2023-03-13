package com.my.github.majiang.community.mytestcommunity.controller;

import com.alibaba.fastjson.JSON;
import com.my.github.majiang.community.mytestcommunity.dto.AccessTokenDTO;
import com.my.github.majiang.community.mytestcommunity.dto.TokenRspDTO;
import com.my.github.majiang.community.mytestcommunity.dto.UserRspDTO;
import com.my.github.majiang.community.mytestcommunity.mapper.UserMapper;
import com.my.github.majiang.community.mytestcommunity.model.User;
import com.my.github.majiang.community.mytestcommunity.util.MyHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private MyHttpUtils utils;

    @Autowired
    public UserMapper userMapper;

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
                           @RequestParam("state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response
    ){
        try {
            AccessTokenDTO accessToken = new AccessTokenDTO()
                    .setClient_id(clientId)
                    .setClient_secret(clientSecret)
                    .setRedirect_uri(redirectUri)
                    .setCode(code);
            String rspToken = utils.post("https://github.com/login/oauth/access_token",
                    JSON.toJSONString(accessToken));
            TokenRspDTO tokenRsp = JSON.parseObject(rspToken, TokenRspDTO.class);
            String token = tokenRsp.getAccess_token();

            String rspUser = utils.get("https://api.github.com/user",
                    "{}","Authorization","token "+token);
            UserRspDTO userRsp = JSON.parseObject(rspUser, UserRspDTO.class);

            if(userRsp!= null && userRsp.getId()!=null){
                // 登录成功
                String uuidToken = String.valueOf(UUID.randomUUID());
                User user = new User()
                        .setToken(uuidToken)
                        .setName(userRsp.getName())
                        .setAccountId(userRsp.getLogin())
                        .setCreateTime(new Timestamp(System.currentTimeMillis()))
                        .setUpdateTime(new Timestamp(System.currentTimeMillis()));
                userMapper.insert(user);
                // 写 cookie 和 session
                response.addCookie(new Cookie("token",uuidToken));
            }else {
                // 登录失败 重新登录
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

}
