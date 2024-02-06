package com.example.personalproject.endpoints;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        // Verifica si la instancia de Retrofit ya ha sido creada
        if (retrofit == null) {
            // Configura un OkHttpClient con un interceptor para agregar una clave de API a las solicitudes
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                    chain -> {
                        // Agrega la clave de API a la solicitud
                        Request request = chain.request().newBuilder()
                                // la key se suele meter como header para que no se vea en la url
                                //pero en esta api no me funciona, porque no la pide como header
                                //sino como parametro
                                .addHeader("key", "79749ec4d2534d149d3506e39b93e280")
                                .build();
                        return chain.proceed(request);
                    }).build();

            // Crea una instancia de Retrofit con la configuración establecida
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.rawg.io/api/") // URL base de la API
                    .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos Java
                    .build();
        }
        return retrofit;
    }
}
