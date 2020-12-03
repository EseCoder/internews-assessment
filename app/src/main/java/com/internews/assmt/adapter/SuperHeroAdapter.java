package com.internews.assmt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.internews.assmt.R;
import com.internews.assmt.model.SuperHero;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.SuperHeroHolder> implements Filterable {

    private Context context;
    private List<SuperHero> supsList;
    private List<SuperHero> supsFilteredList;

    public SuperHeroAdapter(Context context, List<SuperHero> supsList) {
        this.context = context;
        this.supsList = supsList;
        this.supsFilteredList = supsList;
    }

    @NonNull
    @Override
    public SuperHeroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new SuperHeroHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperHeroHolder holder, int position) {
        SuperHero superHero = this.supsList.get(position);
        holder.bind(superHero);
    }

    @Override
    public int getItemCount() {
        return supsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    supsFilteredList = supsList;
                } else {
                    List<SuperHero> filteredList = new ArrayList<>();
                    for (SuperHero superHero : supsList) {

                        // name match condition.
                        // here we are looking for name match
                        if (superHero.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(superHero);
                        }
                    }

                    supsFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = supsFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                supsFilteredList = (ArrayList<SuperHero>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class SuperHeroHolder extends RecyclerView.ViewHolder {

        private Context context;
        private ImageView image;
        private TextView name;

        public SuperHeroHolder(Context context, @NonNull View itemView) {
            super(itemView);

            this.context = context;

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }

        public void bind(SuperHero superHero) {
            String s = superHero.getImage().getUrl();
            if (s != null && !s.isEmpty()) {
                Glide.with(context)
                        .load(s)
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        //.centerCrop().crossFade()
                        .into(image);
            }

            name.setText(String.valueOf(superHero.getName()));
        }
    }
}
