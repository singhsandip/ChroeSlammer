package com.omninos.chroeslammer.Interface;

import com.omninos.chroeslammer.Models.Register_Response_Model;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sandeep on 17-05-2017.
 */

public interface RegisterApi
{
    @FormUrlEncoded
    @POST("/api/Coreslamer/register")
    public void insertUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("device_type") String device_type,
            @Field("reg_id") String fb_token,
            Callback<Register_Response_Model> callback);
}
