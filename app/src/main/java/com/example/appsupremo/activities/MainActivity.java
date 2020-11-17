package com.example.appsupremo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsupremo.R;
import com.example.appsupremo.adapters.RecentSearchAdapter;
import com.example.appsupremo.entities.SuperHero;
import com.example.appsupremo.utils.RecyclerItemClickListener;
import com.example.appsupremo.view_model.SearchHeroViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                s = s.trim();
                saveToSharedPreference(s);
                querySearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        querySearch(getRecentSearches().get(position));
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                }));

    }

    private void init() {
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recent_search_r_view);
        sharedPreferences = getSharedPreferences("RECENT", MODE_PRIVATE);
        gson = new Gson();

        RecentSearchAdapter adapter = new RecentSearchAdapter(getRecentSearches(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void querySearch(String s) {
        SearchHeroViewModel searchHeroViewModel = new SearchHeroViewModel();
        searchHeroViewModel.searchSuperHeroes(s).observe(this, this::handleResponse);
    }

    private void handleResponse(List<SuperHero> superHeroes) {
        if (superHeroes.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Character with given name not found", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Search Successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
        intent.putExtra("Results", (Serializable) superHeroes);
        startActivity(intent);
    }

    private void saveToSharedPreference(String s) {
        List<String> recent = getRecentSearches();
        if (recent.contains(s)) {
            return;
        }
        recent.add(s);
        String json = gson.toJson(recent);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Search", json);
        editor.apply();
    }

    private List<String> getRecentSearches() {
        List<String> recentSearches = new ArrayList<>();
        String json = sharedPreferences.getString("Search", "");
        if (json.isEmpty()) {
            return recentSearches;
        }
        Type type = new TypeToken<List<String>>() {
        }.getType();
        recentSearches = gson.fromJson(json, type);
        return recentSearches;
    }
}
