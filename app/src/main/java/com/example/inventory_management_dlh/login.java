package com.example.inventory_management_dlh;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Admin;
import com.example.Rest.ApiClient;
import com.example.Rest.ApiInterface;
import com.example.database.myDatabaseAdapter;
import com.example.grid.CustomAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    private Button button;
    private EditText username,password;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    myDatabaseAdapter helper;
    Context context;

    ProgressDialog loading;
    ApiInterface apiInterface;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setEmptyText();


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Editable uname = username.getText();
        Editable pass = password.getText();
        String token = "123";

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        button = findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long data = helper.inserData(uname,pass);
//                if(data <= 0){
//                    Toast.makeText(login.this,"sukses insert",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(login.this,"Failed to insert",Toast.LENGTH_SHORT).show();
//                }
//                if(username.getText().toString().equals("tauhid")) {
//                    Intent intent = new Intent(login.this, Home.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(login.this,"Username Salah", Toast.LENGTH_LONG).show();
//                }


                loading = ProgressDialog.show(login.this, null, "Mohon Tunggu", true,false);


//                sendSMS();

                if(uname.toString().equals("")){
                    loading.dismiss();
                    Toast.makeText(login.this,"Oops username sepertinya belum diisi",Toast.LENGTH_SHORT).show();
                    return;
                }else if(pass.toString().equals("")){
                    loading.dismiss();
                    Toast.makeText(login.this,"Oops password sepertinya belum diisi",Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<Admin> session = apiInterface.postAdmin(uname.toString(), pass.toString(), token);
                session.enqueue(new Callback<Admin>() {
                    @Override
                    public void onResponse(Call<Admin> call, Response<Admin> response) {
                            if(response.code() == 200){
                                loading.dismiss();


                                Toast.makeText(login.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                                    Intent home = new Intent(login.this,Home.class);
                                    home.putExtra("name", username.getText().toString());
                                    startActivity(home);
                            }else if(response.code() == 404){
                                loading.dismiss();
                                Toast.makeText(login.this,"Yahh sepertinya username atau password salah" ,Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onFailure(Call<Admin> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(login.this,"Koneksi internet dibutuhkan",Toast.LENGTH_SHORT).show();
                    }

                    public void showResponse(String response){

                    }
                });

            }
        });
    }

    private void setEmptyText(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        username.setText("");
        password.setText("");
    }

    protected void onRestart(){
        super.onRestart();
        Intent intent = new Intent(login.this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendSMS(){

        if(ContextCompat.checkSelfPermission(login.this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(login.this,Manifest.permission.SEND_SMS)){
                sendSMS();
            }else{
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager  = SmsManager.getDefault();
                    smsManager.sendTextMessage("085718437715",null,"SEND SMS LOCAL ANDROID",null,null);
                    Toast.makeText(login.this,"Message is send",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}