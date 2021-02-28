package com.example.inventory_management_dlh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private Button button;
    private EditText username,password;
    myDatabaseAdapter helper;
    Context context;

    ProgressDialog loading;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Editable uname = username.getText();
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
                Call<Admin> session = apiInterface.postAdmin(uname.toString(), token);
                session.enqueue(new Callback<Admin>() {
                    @Override
                    public void onResponse(Call<Admin> call, Response<Admin> response) {
                            if(response.code() == 200){
                                loading.dismiss();
                                Toast.makeText(login.this,"SUKSES",Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(login.this,Home.class);
                                startActivity(home);
                            }else if(response.code() == 404){
                                loading.dismiss();
                                Toast.makeText(login.this,"Username Salah" ,Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onFailure(Call<Admin> call, Throwable t) {
                        Toast.makeText(login.this,"FAILED",Toast.LENGTH_SHORT).show();
                    }

                    public void showResponse(String response){

                    }
                });

            }
        });
    }
}