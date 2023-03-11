package com.my.github.majiang.community.mytestcommunity.util;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author afeatherking
 *      OkHttp
 */
@Component
public class MyHttpUtils {
    private static final OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

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
            return getRequestBody(response);
        }
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Accept","application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return getRequestBody(response);
        }
    }

    public static String getRequestBody(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.getBuffer();

        Charset charset = StandardCharsets.UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(StandardCharsets.UTF_8);
        }
        return buffer.clone().readString(charset);
    }


}
