package com.ichi2.anki;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class PreferencesSearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private PreferencesSearchRecyclerAdapter adapter;

    public PreferencesSearchFragment(PreferencesSearchRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preferences_search,
                container, false);

        //adapter = new PreferencesSearchRecyclerAdapter(preferencesList);

        recyclerView = view.findViewById(R.id.prefs_search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        return view;
    }

    public PreferencesSearchRecyclerAdapter getPreferencesSearchRecyclerAdapter() {
        if (this.isAdded() && adapter != null) {
            return adapter;
        }
        return null;
    }
}

//public class PreferencesSearch extends Activity {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.preferences_search);
//        //handleIntent(getIntent());
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            //use the query to search your data somehow
//        }
//    }
//}
