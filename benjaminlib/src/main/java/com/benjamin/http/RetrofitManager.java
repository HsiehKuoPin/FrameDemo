package com.benjamin.http;

import android.util.Log;

import com.benjamin.http.Interceptor.LogRequestInterceptor;
import com.benjamin.http.Interceptor.LogResponseInterceptor;
import com.benjamin.http.gson.GsonConverterFactory;

import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitManager {
    private final String TAG = "RetrofitManager";
    private Retrofit retrofit;
    private static Map<String, RetrofitManager> apiInstanceMap = new HashMap();
    private static HttpConfig httpConfig;

    public static <T> T create(Class<T> clazz) {
        return create(clazz, httpConfig.getBaseUrl());
    }

    public static <T> T create(Class<T> clazz, String host) {
        if (apiInstanceMap.get(host) == null) {
            RetrofitManager retrofitManager = new RetrofitManager(host);
            apiInstanceMap.put(host, retrofitManager);
        }

        return apiInstanceMap.get(host).retrofit.create(clazz);
    }

    public static void initConfig(HttpConfig config) {
        httpConfig = config;
    }

    public static HttpConfig getConfig() {
        return httpConfig;
    }

    private RetrofitManager(String host) {
        try {
            Builder builder = new OkHttpClient().newBuilder();
            builder.connectTimeout((long) httpConfig.getTimeOut(), TimeUnit.SECONDS);
            builder.readTimeout((long) httpConfig.getTimeOut(), TimeUnit.SECONDS);
            builder.writeTimeout((long) httpConfig.getTimeOut(), TimeUnit.SECONDS);
//            HeaderInterceptor.AccessTokenProvider provider = httpConfig.getTokenProvider();
//            if (provider != null) {
//                builder.addNetworkInterceptor(new HeaderInterceptor(provider));
//            }
            if (httpConfig.isOpenLogger()) {
                builder.addInterceptor(new LogResponseInterceptor());
                builder.addNetworkInterceptor(new LogRequestInterceptor());
//                builder.addInterceptor(new LogRequestInterceptor());
//                builder.addInterceptor(this.getHttpLoggingInterceptor());
            }
            builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts());

//            HttpsURLConnection.setDefaultSSLSocketFactory(createSSLSocketFactory());
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
            builder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            if (!httpConfig.isAllowProxy()) {
                builder.proxy(Proxy.NO_PROXY);
            }
            builder.retryOnConnectionFailure(true);
            this.retrofit = (new Retrofit.Builder()).baseUrl(host).client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(httpConfig.getResultConfig()))
//                    .addConverterFactory(FastJsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

//    private <T> T create(Class<T> clazz) {
//        return this.retrofit.create(clazz);
//    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        Level level = Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new Logger() {
            public void log(String message) {
                Log.d(TAG, message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    private class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignore) {

        }
        return ssfFactory;
    }
}
