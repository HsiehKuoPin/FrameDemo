package com.benjamin.http.exception;

import android.net.ParseException;
import android.os.NetworkOnMainThreadException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

public class HttpExceptionHandler {

    public static HttpException handle(Throwable e) {
        if (e instanceof HttpException) {
            return (HttpException) e;
        }
        HttpException ex = new HttpException(e);
        if (e instanceof retrofit2.HttpException) {
            return RetrofitExceptionHandler.handle((retrofit2.HttpException) e);
        } else if (e instanceof ConnectException) {
            ex.exCode = HttpResultCode.CONNECT_ERROR;
            ex.exMessage = "HTTP连接失败";
        } else if (
//                e instanceof com.alibaba.fastjson.JSONException
                e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex.exCode = HttpResultCode.PARSE_ERROR;
            ex.exMessage = "HTTP JSON解析错误";
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex.exCode = HttpResultCode.SSL_ERROR;
            ex.exMessage = "HTTP证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            ex.exCode = HttpResultCode.TIMEOUT_ERROR;
            ex.exMessage = "HTTP连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex.exCode = HttpResultCode.TIMEOUT_ERROR;
            ex.exMessage = "HTTP读写超时";
        } else if (e instanceof NetworkOnMainThreadException) {
            ex.exCode = HttpResultCode.NETWORK_MAIN_ERROR;
            ex.exMessage = "HTTP主线程访问";
        } else if (e instanceof UnknownHostException) {
            ex.exCode = HttpResultCode.UN_KNOW_HOST;
            ex.exMessage = "无法确定主机的IP地址";
        } else {
            ex.exCode = HttpResultCode.HTTP_UNKNOWN;
            ex.exMessage = "未知异常";
        }
        return ex;
    }

    private static class RetrofitExceptionHandler {
        static HttpException handle(retrofit2.HttpException e) {
            HttpException ex;
            int httpCode = e.code();
            ex = new HttpException(httpCode, e);
            switch (httpCode) {
                case HttpResultCode.BAD_REQUEST:
                    ex.exMessage = "HTTP错误请求";
                    break;
                case HttpResultCode.UNAUTHORIZED:
                    ex.exMessage = "HTTP未经授权";
                    break;
                case HttpResultCode.FORBIDDEN:
                    ex.exMessage = "HTTP被禁用";
                    break;
                case HttpResultCode.NOT_FOUND:
                    ex.exMessage = "HTTP没有找到地址";
                    break;
                case HttpResultCode.REQUEST_TIMEOUT:
                    ex.exMessage = "HTTP请求超时";
                    break;
                case HttpResultCode.INTERNAL_SERVER_ERROR:
                    ex.exMessage = "HTTP服务器内部错误";
                    break;
                case HttpResultCode.BAD_GATEWAY:
                    ex.exMessage = "HTTP无效网关";
                    break;
                case HttpResultCode.SERVICE_UNAVAILABLE:
                    ex.exMessage = "HTTP服务不可用";
                    break;
                case HttpResultCode.GATEWAY_TIMEOUT:
                    ex.exMessage = "HTTP网关超时";
                    break;
                default:
                    ex.exMessage = "未知HTTP异常";
                    break;
            }
            return ex;
        }

    }


}
