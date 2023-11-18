package com.example.personalproject.endpoints;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static Retrofit retrofit;
    public static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                    chain -> {
                        Request request = chain.request().newBuilder()
                                .addHeader("key", "79749ec4d2534d149d3506e39b93e280")
                                .build();
                        return chain.proceed(request);
                    }).build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.rawg.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
