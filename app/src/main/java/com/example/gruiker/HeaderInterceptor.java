package com.example.gruiker;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor
        implements Interceptor {
    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("oauth_consumer_key","tEeQ7Bov3Dv2ezHapHVO3JC1t ")
                .addHeader("oauth_token","894924079-NXWhgTM8wwhP5Ayy8kZf01HNqvtPu4fX3xcsk2ol")
                .addHeader("oauth_signature_method","HMAC-SHA1")
                .addHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .addHeader("oauth_nonce","TQkaRTHzyEG")
                .addHeader("oauth_version","1.0")
                .addHeader("oauth_signature","agoDctVP0N40cn7T6nnk%2FZkhObU%3D")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
