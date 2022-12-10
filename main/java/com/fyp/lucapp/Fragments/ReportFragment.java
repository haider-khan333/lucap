package com.fyp.lucapp.Fragments;

import android.content.ContextWrapper;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fyp.lucapp.Adapters.ReportsAdapterView;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.Models.Data;
import com.fyp.lucapp.R;
import com.fyp.lucapp.databinding.MedicationsCardViewBinding;
import com.fyp.lucapp.databinding.ReportCardViewBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class ReportFragment extends Fragment implements AdapterInterface {

    public ReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ask for read and write permission
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_report, container,
                false);



        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new ReportsAdapterView(Data.fillReport(),
                    this));

        }
        return view;
    }


    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {
        String reportID = binding.reportID.getText().toString();
        String patientName = binding.patientName.getText().toString();
        String doctorName = binding.docName.getText().toString();
        String date = binding.generationDate.getText().toString();
        String time = binding.generationTime.getText().toString();
        String description = Data.reportList.get(position).getDescriptionOfAppointment();

//        String base64 = binding.attachedImage.getText().toString();

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300,
                600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Paint paint = new Paint();

//        page.getCanvas().dra(R.drawable.lucapp, Matrix.IDENTITY_MATRIX, paint);
        page.getCanvas().drawText("Report ID: "+reportID, 10, 10, paint);
        page.getCanvas().drawText("Patient Name: " + patientName, 10, 30, paint);
        page.getCanvas().drawText("Doctor Name: " + doctorName, 10, 50, paint);
        page.getCanvas().drawText("Date: " + date, 10, 70, paint);
        page.getCanvas().drawText("Time: " + time, 10, 90, paint);
        //write down the description on multiple lines
        String[] descriptionLines = description.split(",");
        int y = 110;
        for (String line : descriptionLines) {
            page.getCanvas().drawText(line, 10, y, paint);
            y += 20;
        }
//        page.getCanvas().drawText("Description: " + description, 10, 100, paint);

        pdfDocument.finishPage(page);

        //save pdf
        if(Helper.savePDFFile(getFilePath(), pdfDocument)){
//            Toast.makeText(getActivity(), "PDF saved", Toast.LENGTH_SHORT).show();
            Snackbar.make(binding.getRoot(), "Report Downloaded",
                    Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(binding.getRoot(), "Report Download Failed",
                    Snackbar.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "PDF not saved", Toast.LENGTH_SHORT).show();
        }
//        page.getCanvas().drawText("Image: " + base64, 10, 60, paint);

    }

    @Override
    public void onAlarmClicked(View view, int position) {

    }




    private String getFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getActivity());
        File downloadDirectory=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file=new File(downloadDirectory,"report.pdf");
        System.out.println("File path: "+file.getPath());
        return file.getPath();
    }


}