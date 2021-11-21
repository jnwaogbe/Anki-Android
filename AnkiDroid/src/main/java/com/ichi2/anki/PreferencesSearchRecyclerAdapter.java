package com.ichi2.anki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreferencesSearchRecyclerAdapter extends RecyclerView.Adapter<PreferencesSearchRecyclerAdapter.ViewHolder> implements Filterable
{
    private List<String> preferencesList;
    private List<String> preferencesListAll;

    public PreferencesSearchRecyclerAdapter(List<String> preferencesList) {
        this.preferencesList = preferencesList;
        this.preferencesListAll = new ArrayList<>(preferencesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.preferences_search_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.preferencesName.setText(preferencesList.get(position));
    }


    @Override
    public int getItemCount() {
        return preferencesList.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.clear();
            } else {
                for (String preference: preferencesListAll) {
                    if (preference.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(preference);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            preferencesList.clear();
            preferencesList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView preferencesName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            preferencesName = itemView.findViewById(R.id.prefs_search_name);
            preferencesName.setElegantTextHeight(true);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Should navigate to: " + preferencesList.get(getBindingAdapterPosition()), Toast.LENGTH_SHORT);
        }
    }
}
