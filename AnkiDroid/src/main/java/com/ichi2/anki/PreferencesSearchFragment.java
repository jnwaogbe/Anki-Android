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

/**
 * Preferences Search Fragment
 */
// CS427 https://github.com/ankidroid/Anki-Android/issues/8342
public class PreferencesSearchFragment extends Fragment implements PreferencesSearchRecyclerAdapter.PreferencesSearchListener {
    /**
     * Preferences Search Recycler Adapter
     */
    private final PreferencesSearchRecyclerAdapter adapter;
    /**
     * Preferences Search Options List
     */
    private static List<PreferencesSearchOptions> preferencesList;

    public PreferencesSearchFragment(final List<PreferencesSearchOptions> preferencesList) {
        super();
        this.adapter = new PreferencesSearchRecyclerAdapter(preferencesList, this);
        this.preferencesList = preferencesList;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.preferences_search,
                container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.prefs_search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        final DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        return view;
    }

    public PreferencesSearchRecyclerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onSearchSuggestionSelected(final int position) {
        final PreferencesSearchOptions options = preferencesList.get(position);
        final String searchString = options.getSearchString();
        Toast.makeText(getContext(), searchString, Toast.LENGTH_SHORT).show();

        final PreferenceFragmentCompat prefFragment = options.getFragment();

        final String preferencesKey = options.getPreferencesKey();

        if (prefFragment != null) {
            if (preferencesKey != null) {
                prefFragment.scrollToPreference(preferencesKey);
            }

            getParentFragmentManager()
                    .beginTransaction()
                    .remove(this)
                    .add(R.id.settings_container, prefFragment)
                    .addToBackStack(null)
                    .commit();

            preferencesList.clear();
        }
    }
}
