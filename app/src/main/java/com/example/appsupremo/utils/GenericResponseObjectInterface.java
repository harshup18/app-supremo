package com.example.appsupremo.utils;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

public interface GenericResponseObjectInterface {
    void onSuccess(@NonNull JsonObject response);

    void onError(@NonNull Throwable throwable);

}

