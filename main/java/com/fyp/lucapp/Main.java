package com.fyp.lucapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.fyp.lucapp.Fragments.DoctorListFragment;
import com.fyp.lucapp.Fragments.MedicationsFragment;
import com.fyp.lucapp.Fragments.ReportFragment;
import com.fyp.lucapp.Fragments.ProfileFragment;
import com.fyp.lucapp.Views.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        drawerLayout.closeDrawer(GravityCompat.START);
        //by default selection
        loadFragment(new DoctorListFragment(), 1);


        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_book_appointment:
                    loadFragment(new DoctorListFragment(), 0);
                    toolbar.setTitle("Book Appointment");
                    break;
                case R.id.nav_profile_settings:
                    loadFragment(new ProfileFragment(), 0);
                    toolbar.setTitle("Profile Settings");
                    break;
                case R.id.nav_report:
                    loadFragment(new ReportFragment(), 0);
                    toolbar.setTitle("Medical Report");
                    break;

                case R.id.nav_medication:
                    loadFragment(new MedicationsFragment(), 0);
                    toolbar.setTitle("Medication");
                    break;

                case R.id.nav_logout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Logout");
                    builder.setMessage("Are you sure you want to logout?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        //got login activity
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                        //do nothing
                    });
                    builder.show();
                    break;


            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //ask for logout
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                //got login activity
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                //do nothing
            });
            builder.show();

        }
    }

    public void loadFragment(Fragment fragment, int flag) {
        if (flag == 1) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    fragment).commit();
        }
    }
}

