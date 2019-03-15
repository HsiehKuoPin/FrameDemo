package com.benjamin.http.Interceptor;


import android.util.Log;

import com.benjamin.utils.eighteen.ConvertUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * 全局拦截，打印http响应内容
 * @author user
 */
public class LogResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        long t1 = System.nanoTime();

        Request request = chain.request();
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();

        ResponseBody responseBody = response.body();
        //long contentLength = responseBody.contentLength();
        //String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        //比如 <-- 200 OK http://121.40.227.8:8088/api (36ms)

        if (!JsonFormatter.hasBody(response)) {
            logComm(response, t2, t1);
        } else if (JsonFormatter.bodyEncoded(response.headers())) {
            logComm(response, t2, t1);
        } else {
            BufferedSource source = responseBody.source();
            // Buffer the entire body.
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset UTF8 = Charset.defaultCharset();
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    UTF8 = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    logComm(response, t2, t1);
                    return response;
                }
            }

            if (!JsonFormatter.isPlaintext(buffer)) {
                logComm(response, t2, t1);
                return response;
            }

            if (buffer.size() > 0) {
                //获取Response的body的字符串 并打印
                String bodyStr = buffer.clone().readString(UTF8);
                logBody(response, t2, t1, buffer.size(), bodyStr);
            } else {
                logComm(response, t2, t1);
            }

        }
        return response;
    }

    private void logComm(Response response, long t2, long t1) {
        log(response, t2, t1,0,null);
    }

    private void logBody(Response response, long t2, long t1, long bodySize, String body) {
        log(response, t2, t1, bodySize, body);
    }

    private void log(Response response, long t2, long t1, long bodySize, String body) {
        String headerMsg = String.format(Locale.US, "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers());
        String bodyMsg = body == null ? "" : "body size = " + ConvertUtils.byte2FitMemorySize(bodySize) + "\nresponse body = " + JsonFormatter.json(body);
        Log.d("LogResponseInterceptor", headerMsg + bodyMsg);
    }


}