package com.example.inventory_management_dlh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.AdminData;
import com.example.Rest.ApiClient;
import com.example.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    EditText uname,jbtn,emaill,nohp,alamat;
    Button edit;
    ProgressDialog loading;

    ApiInterface apiInterface;
    Fragment fragment;

    private String username,jabatan,email,Nohp,alamatt,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        username = bundle.getString("username");
        jabatan = bundle.getString("jabatan");
        email = bundle.getString("email");
        Nohp = bundle.getString("no_hp");
        alamatt = bundle.getString("alamat");

        uname = findViewById(R.id.usernameEdit);
        uname.setText(username);

        jbtn = findViewById(R.id.jabatanEdit);
        jbtn.setText(jabatan);

        emaill = findViewById(R.id.emailEdit);
        emaill.setText(email);

        nohp = findViewById(R.id.nohpEdit);
        nohp.setText(Nohp);

        alamat = findViewById(R.id.alamatEdit);
        alamat.setText(alamatt);

        edit = findViewById(R.id.editButton);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(EditProfile.this, null, "Mohon Tunggu", true,false);
                if(uname.getText().toString().equals("")){
                    Toast.makeText(EditProfile.this, "Username Null", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<AdminData> editData = apiInterface.putDataAdmin(id, uname.getText().toString(), jbtn.getText().toString(), emaill.getText().toString(), nohp.getText().toString(), alamat.getText().toString());
                editData.enqueue(new Callback<AdminData>() {
                    @Override
                    public void onResponse(Call<AdminData> call, Response<AdminData> response) {

                        if(response.code() == 200){
                            loading.dismiss();
                            Toast.makeText(EditProfile.this, "Update Profile Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfile.this, Home.class);
                            intent.putExtra("name", username);
                            startActivity(intent);
                        }else{
                            loading.dismiss();
                            Toast.makeText(EditProfile.this,"Failed Update Profile", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AdminData> call, Throwable t) {

                        loading.dismiss();
                        Toast.makeText(EditProfile.this,"Your Connection is Lost", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}