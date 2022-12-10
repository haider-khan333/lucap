package com.fyp.lucapp.Fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.lucapp.Adapters.MedicationsAdapterView;
import com.fyp.lucapp.Helper.AlarmReceiver;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Data;
import com.fyp.lucapp.R;
import com.fyp.lucapp.databinding.MedicationsCardViewBinding;
import com.fyp.lucapp.databinding.ReportCardViewBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MedicationsFragment extends Fragment implements AdapterInterface {


    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public MedicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medications, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.medicationRecyclerView);
        recyclerView.setAdapter(new MedicationsAdapterView(Data.fillMedications(),
                this));


        return view;
    }


    /**
     * Ignore this method
     *
     * @param binding
     * @param position
     */
    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {

    }

    @Override
    public void onAlarmClicked(View view, int position) {
        MedicationsCardViewBinding binding = MedicationsCardViewBinding.bind(view);
        //create a notification Channel
        createNotificationChannel();


        System.out.println("Alarm clicked");
        binding.setAlarm.setOnClickListener(e->{
            showTimePicker();
        });

        binding.cancelAlarm.setOnClickListener(e->{
            cancelAlarm();
        });

        System.out.println("Medicine name : " + binding.medicineName.getText().toString());

    }


    private void cancelAlarm() {
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        if(alarmManager == null){
            alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        }else {
            alarmManager.cancel(pendingIntent);
            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.custom_dialogue);
            //change the message of dialogue message
            TextView message = dialog.findViewById(R.id.message);
            message.setText("Alarm cancelled successfully");

            LottieAnimationView animationView = dialog.findViewById(R.id.animationView);
            animationView.setAnimation(R.raw.cancel_animation);


            dialog.show();

            dialog.findViewById(R.id.ok).setOnClickListener(e->{
                dialog.dismiss();
            });
        }
    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        timePicker.show(getParentFragmentManager(), "timePicker");

        timePicker.addOnPositiveButtonClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            //set the alarm
            setAlarm();
        });
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialogue);
        dialog.show();

        //close the dialog on pressing OK
        dialog.findViewById(R.id.ok).setOnClickListener(v -> {
            dialog.dismiss();
        });
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LucAppMedicationChannel";
            String description = "Channel for LucApp";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new android.app.NotificationChannel("LucApp",
                    name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(android.app.
                    NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}