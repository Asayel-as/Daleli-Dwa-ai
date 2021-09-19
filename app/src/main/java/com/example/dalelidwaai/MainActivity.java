package com.example.dalelidwaai;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  {
    private AppBarConfiguration mAppBarConfiguration;
    private ZXingScannerView scannerView;
    private static final int REQUEST_CAMERA = 1;

    FirebaseAuth auth;
    private FirebaseUser userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser();

        if(userID == null){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_slideshow).setVisible(false);
            menu.findItem(R.id.nav_gallery).setVisible(false);
            menu.findItem(R.id.nav_tools).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(false);
        }

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_logout, R.id.nav_search_results)
                .setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void btn_login(View v){
        if(userID == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

    public void btn_sign_up(View v){
        if(userID == null) {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void scanCode(View view){
        scannerView= new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{
        @Override
        public void handleResult(Result result) {
            String resultCode = result.getText();
            Toast.makeText(MainActivity.this,resultCode,Toast.LENGTH_SHORT).show();

            setContentView(R.layout.fragment_home);
            scannerView.stopCamera();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

}