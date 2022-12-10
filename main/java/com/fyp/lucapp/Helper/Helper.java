package com.fyp.lucapp.Helper;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import com.fyp.lucapp.Models.Data;
import com.fyp.lucapp.Models.Doctors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Helper {

    /**
     * return a doctor from doctor list
     * @return
     */
    public static Doctors getDoctor(int position) {
        return Data.doctors.get(position);
    }

    /**
     * Convert string base64 to bitmap
     */
    public static Bitmap convertBase64ToBitmap(String base64) {
        byte[] decodedString = android.util.Base64.decode(base64, android.util.Base64.DEFAULT);
        return android.graphics.BitmapFactory.decodeByteArray(decodedString,
                0, decodedString.length);
    }

    /**
     * Save PDF file to local storage
     */
    public static boolean savePDFFile(String fileName, PdfDocument pdfDocument) {
        try {
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pdfDocument.writeTo(fileOutputStream);
            fileOutputStream.close();
            pdfDocument.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }






}
