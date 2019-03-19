package com.benjamin.http;

import com.benjamin.http.Interceptor.HeaderInterceptor.AccessTokenProvider;

public class HttpConfig {
    private int DEFAULT_TIMEOUT = 20;
    private String baseUrl;
    private int timeOut = DEFAULT_TIMEOUT;
    private IHttpResultConfig resultConfig;
    private boolean allowProxy = true;
    private boolean openLogger = true;
    private AccessTokenProvider tokenProvider;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isOpenLogger() {
        return openLogger;
    }

    public void setOpenLogger(boolean openLogger) {
        this.openLogger = openLogger;
    }

    public boolean isAllowProxy() {
        return allowProxy;
    }

    public void setAllowProxy(boolean allowProxy) {
        this.allowProxy = allowProxy;
    }

    public IHttpResultConfig getResultConfig() {
        return resultConfig;
    }

    public void setResultConfig(IHttpResultConfig resultConfig) {
        this.resultConfig = resultConfig;
    }

    public AccessTokenProvider getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(AccessTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
}