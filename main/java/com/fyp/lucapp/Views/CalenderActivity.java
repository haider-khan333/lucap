package com.fyp.lucapp.Views;

import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Models.Data;
import java.util.Date;
import com.fyp.lucapp.R;

public class CalenderActivity extends AppCompatActivity {

    private ImageView backButton;
    private CalendarView calendarView;
    private AutoCompleteTextView dropItems;
    private Button nextButton;

    private Date selectedDate;
    private String selectedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        backButton = findViewById(R.id.backButton);
        calendarView = findViewById(R.id.calendarView);
        dropItems = findViewById(R.id.drop_items);
        nextButton = findViewById(R.id.nextButton);

        //back button
        backButton.setOnClickListener(v -> finish());

        //set the adapter for the drop down menu
        String[] items = new String[]{"10:00", "10:30", "11:00", "11:30", "12:00", "12:30"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.items_list, items);
        dropItems.setAdapter(adapter);

        //add listener on the drop down menu
        dropItems.setOnItemClickListener((parent, view, position, id) -> {
            selectedTime = parent.getItemAtPosition(position).toString();
            System.out.println("Selected time: " + selectedTime);
        });


        //get the current date from the calendar
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate=new Date(year,month,dayOfMonth);
            System.out.println("Selected date: " + selectedDate);

        });
        
        nextButton.setOnClickListener(v -> {
            //goto ConfirmAppointmentActivity
            Toast.makeText(this, "Doctor Appointed", Toast.LENGTH_SHORT).show();
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
