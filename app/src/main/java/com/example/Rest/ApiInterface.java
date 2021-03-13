package com.example.Rest;

import com.example.Model.Admin;
import com.example.Model.AdminData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("session_login")
    Call<Admin> postAdmin(@Field("name") String name, @Field("password") String pass, @Field("api_key") String key);

    @FormUrlEncoded
    @POST("session_name")
    Call<AdminData> postDataAdmin(@Field("name") String name);

    @FormUrlEncoded
    @PUT("update")
    Call<AdminData> putDataAdmin(@Field("id") String id, @Field("name") String name, @Field("jabatan") String jabatan,
                                 @Field("email") String email, @Field("no_hp") String nphp, @Field("alamat") String alamat);
}
