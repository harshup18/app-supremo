package com.example.appsupremo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsupremo.R;
import com.example.appsupremo.entities.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{

    private List<SuperHero> superHeroes;
    private Context context;

    public SearchResultAdapter(List<SuperHero> superHeroes, Context context) {
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.super_hero_card_view,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SuperHero superHero = superHeroes.get(position);
        final String imageURI = superHero.getImage().getUrl();
        holder.tvName.setText(superHero.getName());
        holder.tvDescription.setText(superHero.getDescription());
        Picasso.with(context).load(imageURI).into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDescription;
        public ImageView iv_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.name);
            this.tvDescription  = itemView.findViewById(R.id.description);
            this.iv_image = itemView.findViewById(R.id.char_image);
        }
    }
}
