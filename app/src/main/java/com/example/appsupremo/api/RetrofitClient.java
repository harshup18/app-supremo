package com.example.appsupremo.api;

import lombok.Data;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appsupremo.utils.AppConstants.ACCESS_CODE;

/**
 * The type Retrofit client.
 */
@Data
public class RetrofitClient {

    private Retrofit retrofit;
    static final String API_END_POINT = "https://www.superheroapi.com/api.php/" + ACCESS_CODE;

    public Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(API_END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
