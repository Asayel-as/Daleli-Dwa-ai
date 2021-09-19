package com.example.dalelidwaai.ui;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dalelidwaai.R;
import com.example.dalelidwaai.ui.gallery.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class EditProfileFragment extends Fragment {

    private EditProfileViewModel EditProfileViewModel;

    private EditText name;
    private TextView email;
    private Button btn_cancel, btn_update;
    private Spinner AgeSpinner, GenderSpinner;

    private String userID;
    private FirebaseAuth auth;
    private FirebaseFirestore store;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EditProfileViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = auth.getCurrentUser().getUid();

        name = root.findViewById(R.id.fNameEdit);
        email = root.findViewById(R.id.email);
        AgeSpinner = root.findViewById(R.id.age);
        GenderSpinner = root.findViewById(R.id.gender);

        List AgeList = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            AgeList.add(Integer.toString(i));
        }
        final ArrayAdapter<String> AgeArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, AgeList);
        AgeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AgeSpinner.setAdapter(AgeArrayAdapter);

        ArrayList<String> GenderArrayList = new ArrayList<>();
        GenderArrayList.add("Male");
        GenderArrayList.add("Female");
        final ArrayAdapter<String> GenderArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, GenderArrayList);
        GenderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenderSpinner.setAdapter(GenderArrayAdapter);

        if (userID != null) {
            DocumentReference ref = store.collection("Members").document(String.valueOf(userID));
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();

                        StringBuilder fields1 = new StringBuilder("");
                        fields1.append(doc.get("name"));

                        StringBuilder fields2 = new StringBuilder("");
                        fields2.append(doc.get("email"));

                        name.setText(fields1.toString());
                        email.setText(fields2.toString());

                        int AgeSpinnerPosition = AgeArrayAdapter.getPosition(doc.get("age").toString());
                        AgeSpinner.setSelection(AgeSpinnerPosition);

                        int GenderSpinnerPosition = GenderArrayAdapter.getPosition(doc.get("gender").toString());
                        GenderSpinner.setSelection(GenderSpinnerPosition);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }

        btn_cancel = root.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new ProfileFragment());
                fr.commit();
            }
        });

        btn_update = root.findViewById(R.id.btn_update_profile);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference ref = store.collection("Members").document(String.valueOf(userID));
                ref.update("name", name.getText().toString());
                ref.update("age", AgeSpinner.getSelectedItem().toString());
                ref.update("gender", GenderSpinner.getSelectedItem().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            FragmentTransaction fr=getFragmentManager().beginTransaction();
                            fr.replace(R.id.nav_host_fragment,new ProfileFragment());
                            fr.commit();
                        }
                    }
                });
            }
        });
        return root;
    }
}