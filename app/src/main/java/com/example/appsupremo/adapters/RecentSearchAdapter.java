package com.example.appsupremo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsupremo.R;

import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.ViewHolder> {

    private List<String> recentSearches;

    public RecentSearchAdapter(List<String> recentSearches, Context context) {
        this.recentSearches = recentSearches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recent_search_row,parent,false);
        return new RecentSearchAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(recentSearches.get(position));
    }

    @Override
    public int getItemCount() {
        return recentSearches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_recent_search);
        }
    }
}
