package com.benjamin.http.gson;

import com.benjamin.http.IHttpResultConfig;
import com.benjamin.http.exception.HttpException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private static final Charset UTF_8 = Charset.forName("UTF-8");
        private final Gson gson;
        private final TypeAdapter<T> adapter;
        private final IHttpResultConfig resultConfig;

        GsonResponseBodyConverter(Gson gson, Type type, IHttpResultConfig resultConfig) {
            this.gson = gson;
            this.adapter = (TypeAdapter<T>) gson.getAdapter(TypeToken.get(type));
            this.resultConfig = resultConfig;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String resultString = value.string();
            handleHttpCodeWithException(resultString, resultConfig);
            MediaType contentType = value.contentType();
            Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
            InputStream inputStream = new ByteArrayInputStream(resultString.getBytes());
            Reader reader = new InputStreamReader(inputStream, charset);
            JsonReader jsonReader = gson.newJsonReader(reader);
            try {
                T read = adapter.read(jsonReader);
                if (read == null) {
                    try {
                        read = (T) new Object();
                    } catch (Exception e) {
                        throw new RuntimeException("if httpResult data is null,you must use type of Object to receive");
                    }
                }
                return read;
            } finally {
                value.close();
            }
        }

        private void handleHttpCodeWithException(String result, IHttpResultConfig resultConfig) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                int resultCode = jsonObject.getInt(resultConfig.getCodeName());
                if (resultCode != resultConfig.getSuccessCode()) {
                    try {
                        throw new HttpException(resultCode, jsonObject.getString(resultConfig.getMessageName()));
                    } catch (JSONException e) {
                        //throwHttpResultFormatException(resultConfig.getMessageName());
                    }
                }
            } catch (JSONException e) {
                //throwHttpResultFormatException(resultConfig.getCodeName());
            }
        }
    }