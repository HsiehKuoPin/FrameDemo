package com.benjamin.http.gson;

import com.benjamin.http.DefaultHttpResultConfig;
import com.benjamin.http.IHttpResultConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public final class GsonConverterFactory extends Converter.Factory {

    public static GsonConverterFactory create() {
        return create(new GsonBuilder(), null);
    }

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static GsonConverterFactory create(IHttpResultConfig resultConfig) {
        return create(new GsonBuilder(), resultConfig);
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static GsonConverterFactory create(GsonBuilder gsonBuilder, IHttpResultConfig resultConfig) {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
        }
        if (resultConfig == null) {
            resultConfig = new DefaultHttpResultConfig();
        }
        return new GsonConverterFactory(gsonBuilder, resultConfig);
    }

    private final Gson gson;

    private final Gson dataParseGson;

    private final IHttpResultConfig resultConfig;

    private GsonConverterFactory(GsonBuilder gsonBuilder, IHttpResultConfig resultConfig) {
        this.gson = gsonBuilder.create();
        this.dataParseGson = gsonBuilder.registerTypeAdapterFactory(new ItemTypeAdapterFactory(resultConfig.getDataName())).create();
        this.resultConfig = resultConfig;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, retrofit2.Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            retrofit2.Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(dataParseGson, type, resultConfig);
    }
}