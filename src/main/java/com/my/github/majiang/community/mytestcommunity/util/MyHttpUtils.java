package com.my.github.majiang.community.mytestcommunity.util;

import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;

/**
 * @author afeatherking
 *      OkHttp
 */
@Component
public class MyHttpUtils {
    private static final OkHttpClient client;

    static {
        // 支持https请求，绕过验证
        X509TrustManager manager = SSLSocketClientUtil.getX509TrustManager();
        client = new OkHttpClient.Builder()
                // 忽略校验
                .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(manager), manager)
                .hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier())
                .build();
    }

    public String get(String url, String s, String headerName, String headerContent) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(headerName,headerContent)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return String.valueOf(response.body());
        }
    }

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Accept","application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return String.valueOf(response.body());
        }
    }


}
