package com.omninos.chroeslammer.Interface;

import com.omninos.chroeslammer.Models.Login_Response_Model;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sandeep on 17-05-2017.
 */

public interface Login_Api
{
    @FormUrlEncoded
    @POST("/api/Coreslamer/login")
    public void userLogin(
            @Field("email") String email,
            @Field("password") String password,
            @Field("reg_id") String fb_token,
            Callback<Login_Response_Model> callback);
}
