package com.benjamin.http.exception;

/**
 * @author Simon
 * @date 2017/10/20
 */

public class HttpResultCode {

    public static final int BAD_REQUEST = 400;

    public static final int UNAUTHORIZED = 401;

    public static final int FORBIDDEN = 403;

    public static final int NOT_FOUND = 404;

    public static final int REQUEST_TIMEOUT = 408;

    public static final int INTERNAL_SERVER_ERROR = 500;

    public static final int BAD_GATEWAY = 502;

    public static final int SERVICE_UNAVAILABLE = 503;

    public static final int GATEWAY_TIMEOUT = 504;


    public static final int HTTP_UNKNOWN = 1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1002;

    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1005;

    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1006;

    /**
     * 主线程访问网络
     */
    public static final int NETWORK_MAIN_ERROR = 1007;

    public static final int UN_KNOW_HOST = 1011;

}
