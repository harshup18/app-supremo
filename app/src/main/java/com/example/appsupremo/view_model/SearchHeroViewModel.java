package com.example.appsupremo.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.appsupremo.entities.SuperHero;
import com.example.appsupremo.repositiory.SuperHeroSearchRepo;
import com.example.appsupremo.utils.GenericResponseObjectInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SearchHeroViewModel extends ViewModel {

    private MutableLiveData<List<SuperHero>> mutableSuperHeroes;
    private SuperHeroSearchRepo superHeroSearchRepo;
    private Gson gson;

    public SearchHeroViewModel() {
        superHeroSearchRepo = new SuperHeroSearchRepo();
        gson = new Gson();
    }

    public MutableLiveData<List<SuperHero>> searchSuperHeroes(String s){
        mutableSuperHeroes = new MutableLiveData<>();
        getSearchResult(s);
        return mutableSuperHeroes;
    }

    private void getSearchResult(String s) {
        superHeroSearchRepo.getSearchHeroes(s, new GenericResponseObjectInterface() {
            @Override
            public void onSuccess(@NonNull JsonObject response) {
                List<SuperHero> superHeroes = new ArrayList<>();
                if (response.has("error")) {
                    mutableSuperHeroes.setValue(superHeroes);
                } else {

                    JsonArray jsonArray = response.getAsJsonArray("results");
                    for (JsonElement jsonElement : jsonArray) {
                        SuperHero superHero = gson.fromJson(jsonElement, SuperHero.class);
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        superHero.setDescription(jsonObject.get("connections").getAsJsonObject().get("group-affiliation").getAsString());
                        superHero.getAppearance().setEyeColor(jsonObject.get("appearance").getAsJsonObject().get("eye-color").getAsString());
                        superHero.getAppearance().setHairColor(jsonObject.get("appearance").getAsJsonObject().get("hair-color").getAsString());
                        superHeroes.add(superHero);
                    }
                    mutableSuperHeroes.setValue(superHeroes);
                }}

            @Override
            public void onError(@NonNull Throwable throwable) {

            }
        });
    }
}
