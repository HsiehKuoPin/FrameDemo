package com.benjamin.http.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class ItemTypeAdapterFactory implements TypeAdapterFactory {

        private final String dataName;

        ItemTypeAdapterFactory(String dataName) {
            this.dataName = dataName;
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);

            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                //当获取了http请求的数据后，获得data所对应的值
                @Override
                public T read(JsonReader in) throws IOException {
                    JsonElement jsonElement = elementTypeAdapter.read(in);
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (jsonObject.has(dataName)) {
                            jsonElement = jsonObject.get(dataName);
                        }
                    }
                    return delegate.fromJsonTree(jsonElement);
                }

            }.nullSafe();
        }
    }