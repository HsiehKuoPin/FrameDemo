package com.benjamin.http.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String ACCEPT = "application/json;charset=UTF-8";
    private static final String CONNECTION = "close";
    private HeaderInterceptor.AccessTokenProvider tokenProvider;

    public HeaderInterceptor(HeaderInterceptor.AccessTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (request.header("Token") == null) {
            builder.addHeader("Authorization", "Bearer " + this.getAccessToken());
        } else {
            builder.removeHeader("Token");
        }

        builder.addHeader("Content-Type", "application/json;charset=UTF-8").addHeader("Accept", "application/json;charset=UTF-8").addHeader("Connection", "close");
        return chain.proceed(builder.build());
    }

    private synchronized String getAccessToken() {
        return this.tokenProvider.getAccessToken();
    }

    public interface AccessTokenProvider {
        String getAccessToken();
    }
}