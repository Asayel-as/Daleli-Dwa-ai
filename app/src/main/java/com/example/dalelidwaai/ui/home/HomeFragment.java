package com.example.dalelidwaai.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dalelidwaai.R;
import com.example.dalelidwaai.model.Medication;
import com.example.dalelidwaai.ui.MedicationDetailsFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    AutoCompleteTextView searchEdit;

    DatabaseReference ref;
    FirebaseFirestore store;
    List<Medication> medications = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ref = FirebaseDatabase.getInstance().getReference("Medicine");
        store = FirebaseFirestore.getInstance();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medications.clear();
                for (final DataSnapshot innerDataSnapshot : dataSnapshot.getChildren()) {
                    medications.add(innerDataSnapshot.getValue(Medication.class));
                }

                ArrayAdapter suggestion = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, medications);
                searchEdit.setAdapter(suggestion);
                searchEdit.setThreshold(1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchEdit = root.findViewById(R.id.searchEdit);
        searchEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medication medication = (Medication) parent.getItemAtPosition(position);
                MedicationDetailsFragment fragment = new MedicationDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("medicine", medication);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment, MedicationDetailsFragment.class.getName())
                        .addToBackStack(MedicationDetailsFragment.class.getName()).commit();
            }
        });

        final Button signupButton = root.findViewById(R.id.signUpButton);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                signupButton.setText(s);
            }
        });

        final Button loginButton = root.findViewById(R.id.loginButton);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                loginButton.setText(s);
            }
        });
        return root;
    }


}