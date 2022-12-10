package com.fyp.lucapp.Adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Medications;
import com.fyp.lucapp.databinding.MedicationsCardViewBinding;


import java.util.ArrayList;

public class MedicationsAdapterView extends RecyclerView.Adapter<MedicationsAdapterView.ViewHolder> {

    private ArrayList<Medications> medicationList;
    private AdapterInterface adapterInterface;

    public MedicationsAdapterView(ArrayList<Medications> medicationList
            , AdapterInterface adapterInterface) {
        this.medicationList = medicationList;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(MedicationsCardViewBinding.inflate(LayoutInflater.
                        from(parent.getContext()),
                parent, false), adapterInterface);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Medications medication = medicationList.get(position);
        holder.medicineName.setText(medication.getMedicineName());
        holder.medicineGrams.setText(medication.getMedicineGrams());
        holder.startDate.setText(medication.getStartDate().toString());
        holder.endDate.setText(medication.getEndDate().toString());
        //get the values from list and print them on the text views
        //clear the text from medicine text View
        holder.medicineTime.setText("");
        for(int i = 0; i < medication.getMedicineTime().size(); i++){
            System.out.println("size of list is : " + medication.getMedicineTime().size());
            holder.medicineTime.append(medication.getMedicineTime().get(i) + "\n");
        }


//        medication.getMedicineTime().forEach(time -> holder.medicineTime.append(time + "\n"));
        holder.medicineGenerationTime.setText(medication.getMedicineGeneratedTime().
                toString());


    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView medicineName;
        private TextView medicineGrams;
        private TextView startDate;
        private TextView endDate;
        private TextView medicineTime;
        private TextView medicineGenerationTime;
        private TextView addAlarm;
        private TextView cancelAlarm;
        private AdapterInterface adapterInterface;

        private MedicationsCardViewBinding binding;

        public ViewHolder(MedicationsCardViewBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());


            this.medicineName = binding.medicineName;
            this.medicineGrams = binding.medicineGrams;
            this.startDate = binding.startDate;
            this.endDate = binding.endDate;
            this.medicineTime = binding.medicineTime;
            this.medicineGenerationTime = binding.medicineGeneratedTime;
            this.addAlarm = binding.setAlarm;
            this.cancelAlarm = binding.cancelAlarm;
            this.adapterInterface = adapterInterface;
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           adapterInterface.onAlarmClicked(v, getAdapterPosition());
        }
    }
}
