package com.benjamin.http.Interceptor;


import android.util.Log;

import com.benjamin.utils.eighteen.ConvertUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 全局拦截，打印http请求内容
 */
public class LogRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        RequestBody requestBody = request.newBuilder().build().body();

        if (requestBody == null) {
            logComm(request);
        } else if (JsonFormatter.bodyEncoded(request.headers())) {
            logComm(request);
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            //编码设为UTF-8
            Charset UTF8 = Charset.defaultCharset();
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                UTF8 = contentType.charset(UTF8);
            }

            String bodyStr = buffer.readString(UTF8);
            if (JsonFormatter.isPlaintext(buffer) && isJson(bodyStr)) {
                logBody(request, buffer.size(), bodyStr);
            } else {
                logComm(request);
            }
        }
        Response proceed = chain.proceed(request);
        return proceed;
    }

    private boolean isJson(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private void logComm(Request request) {
        log(request, 0, null);
    }

    private void logBody(Request request, long bodySize, String body) {
        log(request, bodySize, body);
    }

    private void log(Request request, long bodySize, String body) {
        String headerMsg = String.format("Sending request %s use %s%n%s", request.url(), request.method(), request.headers());
        String bodyMsg = body == null ? "" : "body size = " + ConvertUtils.byte2FitMemorySize(bodySize) + "\nrequest body = " + JsonFormatter.json(body);
        Log.d("LogRequestInterceptor", headerMsg + bodyMsg);
    }
}