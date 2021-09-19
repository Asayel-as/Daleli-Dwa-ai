package com.example.dalelidwaai.ui.Logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dalelidwaai.MainActivity;
import com.example.dalelidwaai.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    private LogoutViewModel logoutViewModel;
    FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        startActivity(new Intent(getContext(), MainActivity.class));
        return root;
    }
}