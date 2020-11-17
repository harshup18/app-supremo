package com.example.appsupremo.repositiory;

import android.util.Log;
import com.example.appsupremo.api.RetrofitClient;
import com.example.appsupremo.api.SuperHeroEndPoint;
import com.example.appsupremo.utils.GenericResponseObjectInterface;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuperHeroSearchRepo {

    private RetrofitClient retrofitClient;

    public SuperHeroSearchRepo() {
        this.retrofitClient = new RetrofitClient();
    }

    public void getSearchHeroes(String s, final GenericResponseObjectInterface callback) {
        Call<JsonObject> call = retrofitClient.getRetrofitInstance().create(SuperHeroEndPoint.class).getSuperHeroFromName(s);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                assert response.body() != null;
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("---FAILURE---", t.getMessage());
                callback.onError(t);
            }
        });
    }
}
