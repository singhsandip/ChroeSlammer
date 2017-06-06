package com.omninos.chroeslammer.Interface;

import com.omninos.chroeslammer.Models.GetProfile_Response_Model;
import com.omninos.chroeslammer.Models.Login_Response_Model;
import com.omninos.chroeslammer.Models.UpdateProfileModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by sandeep on 19-05-2017.
 */

public interface Get_Profile_Api
{
    @FormUrlEncoded
    @POST("/api/Coreslamer/get_profile")
    public void getProfileDetails(
            @Field("id") String id,
            Callback<GetProfile_Response_Model> respponse);

    @Multipart
    @POST("/api/Coreslamer/update_profile")
    public void updateProflie(
            @Part("user_id") String id,
            @Part("image") TypedFile image,
            @Part("name") String user_name,
            @Part("category") String category,
            @Part("address") String address,
            @Part("second_category") String second_category,
            Callback<UpdateProfileModel> respponse);


}
