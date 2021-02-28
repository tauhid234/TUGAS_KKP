package com.example.Rest;

import com.example.Model.Admin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("session_login")
    Call<Admin> postAdmin(@Field("name") String name, @Field("api_key") String key);
}
