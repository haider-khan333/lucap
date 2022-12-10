package com.fyp.lucapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Models.Doctors;
import com.fyp.lucapp.R;

public class AppointDoctorActivity extends AppCompatActivity {

    private Button nextButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_doctor);

        //get data from intent
        Doctors doctors = (Doctors) getIntent().getSerializableExtra("doctor");
        Button nextButton = findViewById(R.id.makeAppointment);
        nextButton.setOnClickListener(v -> {

            //goto CalenderActivity
            Intent intent = new Intent(this, CalenderActivity.class);
            intent.putExtra("doctor", doctors);
            startActivity(intent);
       });

        backButton = findViewById(R.id.backButton);
        //back button
        backButton.setOnClickListener(v -> finish());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

