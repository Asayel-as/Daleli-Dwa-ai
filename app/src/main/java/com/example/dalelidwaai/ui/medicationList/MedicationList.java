package com.example.dalelidwaai.ui.medicationList;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalelidwaai.Cache;
import com.example.dalelidwaai.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MedicationList extends Fragment{

    private RecyclerView recyclerView;

    private Cache cache;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return root;
    }

    MedicationListAdapter medicationListAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cache = new Cache(requireContext());
        medicationListAdapter = new MedicationListAdapter();
        recyclerView = view.findViewById(R.id.medicationsRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(medicationListAdapter);
        refreshItems();

    }

    private void refreshItems() { medicationListAdapter.setItems(cache.getAllMedications()); }
}