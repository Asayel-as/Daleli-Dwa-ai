package com.example.dalelidwaai.ui.SearchResults;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.dalelidwaai.R;
import com.example.dalelidwaai.ui.MedicineInformationFragment;

import java.util.ArrayList;

public class SearchResultsFragment extends Fragment {

    private SearchResultsViewModel searchResultsViewModel;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_results, container, false);
        searchResultsViewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("asayel");
        arrayList.add("awad");
        arrayList.add("alsulami");
        arrayList.add("sara");
        arrayList.add("alharbi");
        arrayList.add("bayan");
        arrayList.add("soror");
        arrayList.add("lyan");
        arrayList.add("fahad");
        arrayList.add("fatani");
        arrayList.add("meyaad");
        arrayList.add("reemullah");
        arrayList.add("asayel");
        arrayList.add("awad");
        arrayList.add("alsulami");
        arrayList.add("sara");
        arrayList.add("alharbi");
        arrayList.add("bayan");
        arrayList.add("soror");
        arrayList.add("lyan");
        arrayList.add("fahad");
        arrayList.add("fatani");
        arrayList.add("meyaad");
        arrayList.add("reemullah");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new MedicineInformationFragment());
                fr.commit();
            }
        });

        return root;
    }
}