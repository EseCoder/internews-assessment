package com.internews.assmt.network;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.zip.GZIPOutputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JSONConverterFactory extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final String TAG = "JsonConverterFactory";

    public static JSONConverterFactory create() {
        return new JSONConverterFactory();
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JSONResponseBodyConverter<>();
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return new JSONRequestBodyConverter<>();
    }

    public static class JSONRequestBodyConverter<T> implements Converter<T, RequestBody> {

        public RequestBody convert(T value) throws IOException {
            if (value instanceof JSONObject) {
                JSONObject requestJSON = (JSONObject) value;
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                GZIPOutputStream stream = new GZIPOutputStream(byteOut);
                byte buff[] = requestJSON.toString().getBytes("UTF-8");
                stream.write(buff, 0, buff.length);
                stream.flush();
                stream.close();
                byteOut.flush();
                return RequestBody.create(MEDIA_TYPE, byteOut.toByteArray());
            }
            return RequestBody.create(MEDIA_TYPE, "{}".getBytes());
        }
    }

    public static class JSONResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        public T convert(ResponseBody value) {
            try {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                InputStream inputStream = value.byteStream();
                //GZIPInputStream zip = new GZIPInputStream(inputStream);
                byte buff[] = new byte[1024 * 10];
                int i;
                while ((i = inputStream.read(buff)) != -1) {
                    output.write(buff, 0, i);
                }
                inputStream.close();
                output.flush();
                return (T) new JSONObject(output.toString("UTF-8"));
            } catch (Exception err) {
                err.printStackTrace();
            } finally {
                value.close();
            }
            return null;
        }
    }
}
