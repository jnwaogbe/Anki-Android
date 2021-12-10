package com.ichi2.anki;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreferencesSearchFragment extends Fragment implements PreferencesSearchRecyclerAdapter.PreferencesSearchListener {
    private RecyclerView recyclerView;
    private PreferencesSearchRecyclerAdapter adapter;
    private List<PreferencesSearchOptions> preferencesList;

    public PreferencesSearchFragment(List<PreferencesSearchOptions> preferencesList) {
        this.adapter = new PreferencesSearchRecyclerAdapter(preferencesList, this);
        this.preferencesList = preferencesList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preferences_search,
                container, false);

        recyclerView = view.findViewById(R.id.prefs_search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        return view;
    }

    public PreferencesSearchRecyclerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onSearchSuggestionSelected(int position) {
        PreferencesSearchOptions options = preferencesList.get(position);
        String searchString = options.getSearchString();
        Toast.makeText(getContext(), searchString, Toast.LENGTH_SHORT).show();

        //preferenceFragment = (Fragment)(Class.forName(fragmentName).newInstance());
        PreferenceFragmentCompat preferenceFragment = options.getFragment();

        String preferencesKey = options.getPreferencesKey();

        if (preferenceFragment != null) {
            if (preferencesKey != null) {
                preferenceFragment.scrollToPreference(preferencesKey);
            }

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings_container, preferenceFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
