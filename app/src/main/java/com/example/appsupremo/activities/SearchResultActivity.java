package com.example.appsupremo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsupremo.R;
import com.example.appsupremo.adapters.SearchResultAdapter;
import com.example.appsupremo.entities.SuperHero;
import com.example.appsupremo.utils.RecyclerItemClickListener;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<SuperHero> superHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(SearchResultActivity.this, CharacterPageActivity.class);
                        i.putExtra("Character", superHeroes.get(position));
                        startActivity(i);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                }));
    }

    private void init() {
        Intent intent = getIntent();
        superHeroes = (List<SuperHero>) intent.getSerializableExtra("Results");
        recyclerView = findViewById(R.id.search_result);
        SearchResultAdapter adapter = new SearchResultAdapter(superHeroes, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SearchResultActivity.this, MainActivity.class);
        startActivity(i);
    }
}
