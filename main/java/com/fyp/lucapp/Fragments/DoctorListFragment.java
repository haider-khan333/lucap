package com.fyp.lucapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.DoctorListAdapterView;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Data;
import com.fyp.lucapp.Models.Doctors;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Views.AppointDoctorActivity;
import com.fyp.lucapp.databinding.ReportCardViewBinding;

public class DoctorListFragment extends Fragment implements AdapterInterface {

    public DoctorListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);


        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new DoctorListAdapterView(Data.fillData(),
                    this));

            //Decoration item
            DividerItemDecoration dividerItemDecoration = new
                    DividerItemDecoration(recyclerView.getContext(),
                    DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        return view;
    }


    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {

    }

    @Override
    public void onAlarmClicked(View view, int position) {
        Doctors doctors = Helper.getDoctor(position);
//            //goto next activity
        Intent intent = new Intent(getActivity(), AppointDoctorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("doctor", doctors);
        intent.putExtras(bundle);
        startActivity(intent);

    }


}
