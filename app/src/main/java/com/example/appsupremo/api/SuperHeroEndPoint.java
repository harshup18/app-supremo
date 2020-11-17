package com.example.appsupremo.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuperHeroEndPoint {

    @GET("search/{name}")
    Call<JsonObject> getSuperHeroFromName(
            @Path("name") String name
    );
}
