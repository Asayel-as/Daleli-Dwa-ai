package com.example.dalelidwaai.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.dalelidwaai.R;
import com.example.dalelidwaai.ui.EditProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingFragment extends Fragment {

    private SettingViewModel toolsViewModel;

    //Switch notification;
    Button resetPassword, editProfile;

    FirebaseAuth auth;
    private FirebaseUser userID;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);


        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser();

        //notification = root.findViewById(R.id.Notifications);
        resetPassword = root.findViewById(R.id.ResetPassword);
        editProfile = root.findViewById(R.id.EditProfile);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.sendPasswordResetEmail(userID.getEmail());
                Toast.makeText(getContext(),"Reset email was sent.",Toast.LENGTH_SHORT).show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
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