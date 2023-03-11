package com.my.github.majiang.community.mytestcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * todo 解决 sun.security.provider.certpath.SunCertPathBuilderException
 * -- 此方法未用到 okhttp
 * */
@SpringBootApplication
public class MyTestCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTestCommunityApplication.class, args);
//        System.setProperty("javax.Net.ssl.trustStore", "D:\\code\\github-code\\my-test-community\\jssecacerts");
    }

}
