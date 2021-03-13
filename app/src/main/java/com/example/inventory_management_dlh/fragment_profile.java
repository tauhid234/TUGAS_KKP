package com.example.inventory_management_dlh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.AdminData;
import com.example.Rest.ApiClient;
import com.example.Rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_profile extends Fragment {

    TextView txt2, jabatan, email, nohp, alamat;
    ApiInterface apiInterface;

    ProgressDialog loading;
    FloatingActionButton btnEdit;
    Context context;

    private String jbt,mail,noHP,alamatt, id;

    public fragment_profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        txt2 = v.findViewById(R.id.usernameProfile);
        String s = getArguments().getString("namaku");

        jabatan = v.findViewById(R.id.jabatan);
        email = v.findViewById(R.id.email);
        nohp = v.findViewById(R.id.no_hp);
        alamat = v.findViewById(R.id.alamat);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        loading = ProgressDialog.show(getActivity(), null, "Mohon Tunggu", true,false);
        Call<AdminData> admData = apiInterface.postDataAdmin(s);
            admData.enqueue(new Callback<AdminData>() {
                @Override
                public void onResponse(Call<AdminData> call, Response<AdminData> response) {
                    if (response.code() == 200) {
                        loading.dismiss();

                        response.body();
                        jbt = response.body().getJabatan();
                        mail = response.body().getEmail();
                        noHP = response.body().getNo_hp();
                        alamatt = response.body().getAlamat();
                        id = response.body().getId();

                        jabatan.setText("Jabatan : "+jbt.toLowerCase());
                        email.setText("Email : "+mail);
                        nohp.setText("Nomer Handphone : "+noHP);
                        alamat.setText("Alamat : "+alamatt);

                        Toast.makeText(getActivity(), "Success Load Profile", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 404) {
                        loading.dismiss();
                        Toast.makeText(getActivity(), "Failed Load Profile", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AdminData> call, Throwable t) {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Koneksi internet dibutuhkan", Toast.LENGTH_SHORT).show();
                }
            });

            btnEdit = v.findViewById(R.id.floatButton);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), "Button Edit Sukses", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), EditProfile.class);
                    intent.putExtra("id", id);
                    intent.putExtra("username", s);
                    intent.putExtra("jabatan", jbt);
                    intent.putExtra("email",mail);
                    intent.putExtra("no_hp",noHP);
                    intent.putExtra("alamat",alamatt);
                    startActivity(intent);
                }
            });



        txt2.setText(s);
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        getActivity().setTitle("Profile");
    }
}