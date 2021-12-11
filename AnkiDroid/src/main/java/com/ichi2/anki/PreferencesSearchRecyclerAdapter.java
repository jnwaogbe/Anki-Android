package com.ichi2.anki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Preferences Search Recylcer Adapter
 */
// CS427 https://github.com/ankidroid/Anki-Android/issues/8342
public class PreferencesSearchRecyclerAdapter extends RecyclerView.Adapter<PreferencesSearchRecyclerAdapter.ViewHolder> implements Filterable
{
    /**
     * List of preferences search options to be filtered
     */
    private static final List<PreferencesSearchOptions> PREF_LIST;
    /**
     * List of all preferences search options
     */
    private static final List<PreferencesSearchOptions> PREF_LIST_ALL;
    /**
     * Listener for search suggestion typing
     */
    private static final PreferencesSearchListener LISTENER;


    /**
     * Default constructor
     * @param preferencesList
     * @param LISTENER
     */
    public PreferencesSearchRecyclerAdapter( final List<PreferencesSearchOptions> preferencesList, final PreferencesSearchListener listener) {
        super();
        this.PREF_LIST = preferencesList;
        this.PREF_LIST_ALL = new ArrayList<>(preferencesList);
        this.LISTENER = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.preferences_search_list_item, parent, false);

        return new ViewHolder(view, LISTENER);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.preferencesName.setText(PREF_LIST.get(position).getSearchString());
    }


    @Override
    public int getItemCount() {
        return PREF_LIST.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }


    /**
     * Preferences Search Options filter
     */
    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(final CharSequence charSequence) {
            final List<PreferencesSearchOptions> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.clear();
            } else {
                for (final PreferencesSearchOptions options: PREF_LIST_ALL) {
                    final String preference = options.getSearchString();
                    if (preference.toLowerCase(Locale.ENGLISH).contains(charSequence.toString().toLowerCase(Locale.ENGLISH))) {
                        filteredList.add(options);
                    }
                }
            }

            final FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }


        @Override
        protected void publishResults(final CharSequence charSequence, final FilterResults filterResults) {
            PREF_LIST.clear();
            PREF_LIST.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };



    /**
     * ViewHolder for Preferences Search Recycler Adapter
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * Preferences Search Listener
         */
        private static final PreferencesSearchListener LISTENER;
        /**
         * Preferences Name
         */
        private static final TextView PREF_NAME;


        /**
         * Constructor for ViewHolder
         * @param itemView
         * @param listener
         */
        public ViewHolder(@NonNull final View itemView, final PreferencesSearchListener listener) {
            super(itemView);

            PREF_NAME = itemView.findViewById(R.id.prefs_search_name);
            PREF_NAME.setElegantTextHeight(true);

            itemView.setOnClickListener(this);
            this.LISTENER = listener;

        }

        @Override
        public void onClick(final View view) {
            this.listener.onSearchSuggestionSelected(getBindingAdapterPosition());
        }
    }



    /**
     * Preferences Search Listener Interface
     */
    // CS427 https://github.com/ankidroid/Anki-Android/issues/8342
    public interface PreferencesSearchListener {
        /**
         * Performs action depending on search suggestion selected
         * @param position Index of position in search suggestion list
         */
        void onSearchSuggestionSelected(int position);
    }
}
