package com.fyp.lucapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Report;
import com.fyp.lucapp.databinding.ReportCardViewBinding;

import java.util.ArrayList;

public class ReportsAdapterView extends RecyclerView.Adapter<ReportsAdapterView.ViewHolder> {

    private ArrayList<Report> reportList;
    private AdapterInterface adapterInterface;
    private TextView readMore;

    public ReportsAdapterView(ArrayList<Report> reportList
    ,AdapterInterface adapterInterface) {
        this.reportList = reportList;
        this.adapterInterface = adapterInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ReportCardViewBinding.inflate(LayoutInflater.
                        from(parent.getContext()),
                parent, false), adapterInterface);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.attachedImage.setImageBitmap(Helper.convertBase64ToBitmap(
//                reportList.get(position).getDetectedImage()));
        holder.date.setText(reportList.get(position).getReportGenerationDate().toString());
        holder.time.setText(reportList.get(position).getReportGenerationTime().toString());
        holder.patientName.setText(reportList.get(position).getPatientName());
        holder.doctorName.setText(reportList.get(position).getDoctorName());
        holder.reportID.setText(reportList.get(position).getReportID());
        holder.description.setText(reportList.get(position).getDescriptionOfAppointment());

        //if the description characters are more than 100 then show only 100 characters
        if (holder.description.getText().length() > 100) {
            holder.description.setText(holder.description.getText().
                    toString().substring(0, 100) +
                    "READ MORE ...");
        }

    }

    @Override
    public int getItemCount() {
       return reportList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public  ImageView attachedImage;
        public  TextView time,date,reportID,patientName,doctorName,description;

        public ViewHolder(ReportCardViewBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());
//            attachedImage = binding.detectedImage;
            time = binding.generationTime;
            date = binding.generationDate;
            reportID = binding.reportID;
            patientName = binding.patientName;
            doctorName = binding.docName;
            description = binding.reportDescription;

//            binding.getRoot().setOnClickListener(v -> {
//                if (adapterInterface != null) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        adapterInterface.onItemClicked(position);
//                    }
//                }
//            });

            binding.downloadButton.setOnClickListener(v -> {
                if (adapterInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        adapterInterface.onDownloadClicked(binding, position);
                    }
                }
            });
        }

    }
}
