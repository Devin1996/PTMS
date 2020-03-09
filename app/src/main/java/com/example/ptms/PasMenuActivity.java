package com.example.ptms;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import io.paperdb.Paper;

public class PasMenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference PasDatabaseRef;
    String PasId;

    private AppBarConfiguration mAppBarConfiguration;
    private Boolean currentLogOutPasStatus= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        PasId = currentUser.getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String PassengerId = intent.getStringExtra("PasId");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
                myRef.setValue("Just Checking"+PassengerId);
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(),"Home is Selected",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_gallery:
                        Toast.makeText(getApplicationContext(),"nav_gallery is Selected",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_slideshow:
                        Toast.makeText(getApplicationContext(),"nav_slideshow is Selected",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_tools:
                        Toast.makeText(getApplicationContext(),"nav_tools is Selected",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_share:
                        Intent settingsIntent = new Intent(PasMenuActivity.this, PassSettingsActivity.class);
                        startActivity(settingsIntent);
                        break;

                    case R.id.nav_send:
                        currentLogOutPasStatus= true;
                        mAuth.signOut();
                        Paper.book().destroy();

                        Intent logoutIntent = new Intent(PasMenuActivity.this, PasMainActivity.class);
                        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(logoutIntent);
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pas_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }






}
