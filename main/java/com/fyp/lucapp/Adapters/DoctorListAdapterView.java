package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Doctors;
import com.fyp.lucapp.databinding.FragmentSingleAppointmentBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Doctors}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DoctorListAdapterView extends RecyclerView.Adapter<DoctorListAdapterView.ViewHolder> {

    private final List<Doctors> mValues;
    private final AdapterInterface adapterInterface;

    public DoctorListAdapterView(List<Doctors> items
            , AdapterInterface adapterInterface) {
        mValues = items;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentSingleAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false), adapterInterface);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(mValues.get(position).getPersonName());
        holder.speciality.setText(mValues.get(position).getSpecialization());
        holder.rating.setText(mValues.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView image;
        public final TextView name;
        public final TextView speciality;
        public final TextView rating;
        public final AdapterInterface adapterInterface;

        public ViewHolder(FragmentSingleAppointmentBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());
            this.image = binding.docImage;
            this.name = binding.doctorName;
            this.speciality = binding.speciality;
            this.rating = binding.rating;
            this.adapterInterface = adapterInterface;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterInterface.onAlarmClicked(view, getAdapterPosition());

        }
    }


}