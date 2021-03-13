package com.example.inventory_management_dlh;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Admin;

import java.util.Calendar;

public class Inventory extends AppCompatActivity {

    private TextView txt;
    private ImageView imageView;
    private Button btnChoose;
    private TextView Name;

    private String NAME_IS = "nama";

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    private static final int RequestPermissionCode = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);



//        imageView = findViewById(R.id.img);
//        btnChoose = findViewById(R.id.choose_file);
//
//        EnableRuntimePermission();
//        btnChoose.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                }else{
//                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, 7);
//                }
//            }
//        });

        txt = findViewById(R.id.input_tgl);
        txt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Tampiltanggal();
            }
        });
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 7 && resultCode == RESULT_OK) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
//        }
//    }
//
//    public void EnableRuntimePermission(){
//        if (ActivityCompat.shouldShowRequestPermissionRationale(Inventory.this,
//                Manifest.permission.CAMERA)) {
//            Toast.makeText(Inventory.this,"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(Inventory.this,new String[]{
//                    Manifest.permission.CAMERA}, RequestPermissionCode);
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
//        switch (requestCode) {
//            case RequestPermissionCode:
//                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(Inventory.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(Inventory.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Tampiltanggal(){
        final Calendar cldr = Calendar.getInstance();
        int th = cldr.get(Calendar.YEAR);
        int mth = cldr.get(Calendar.MONTH);
        int day = cldr.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dg = new DatePickerDialog(Inventory.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txt.setText(year+"-"+month+"-"+dayOfMonth);
            }
        }, th,mth,day);
        dg.show();
    }
}