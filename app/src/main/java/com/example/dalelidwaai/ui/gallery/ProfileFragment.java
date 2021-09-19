package com.example.dalelidwaai.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.dalelidwaai.R;
import com.example.dalelidwaai.ui.EditProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;

    private TextView editText;
    private TextView name, email, gender, age;

    private String userID;
    private FirebaseAuth auth;
    private FirebaseFirestore store;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = auth.getCurrentUser().getUid();

        name = root.findViewById(R.id.Name);
        email = root.findViewById(R.id.email);
        gender = root.findViewById(R.id.gender);
        age = root.findViewById(R.id.age);

        if (userID != null) {
            DocumentReference ref = store.collection("Members").document(String.valueOf(userID));
            ref.get().addOnCompleteListener(new OnCompleteListener <DocumentSnapshot> () {
                @Override
                public void onComplete(@NonNull Task <DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();

                        StringBuilder fields1 = new StringBuilder("");
                        fields1.append(doc.get("name"));

                        StringBuilder fields2 = new StringBuilder("");
                        fields2.append(doc.get("email"));

                        StringBuilder fields3 = new StringBuilder("");
                        fields3.append(doc.get("gender"));

                        StringBuilder fields4 = new StringBuilder("");
                        fields4.append(doc.get("age"));

                        name.setText(fields1.toString());
                        email.setText(fields2.toString());
                        gender.setText(fields3.toString());
                        age.setText(fields4.toString());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }

        editText = root.findViewById(R.id.edit);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new EditProfileFragment());
                fr.commit();
            }
        });
        return root;
    }
}