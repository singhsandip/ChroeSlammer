package com.omninos.chroeslammer.Interface;

import com.omninos.chroeslammer.Models.PostCoreResponseModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by sandeep on 18-05-2017.
 */

public interface Post_Chore_Api
{
    @Multipart
    @POST("/api/Coreslamer/create_job")
    public void postChore(
            @Part("user_id") String user_id,
            @Part("title") String title,
            @Part("description") String description,
            @Part("location") String location,
            @Part("image") TypedFile image,
            @Part("budget") String budget,
            @Part("category") String category,
            Callback<PostCoreResponseModel> callback);
}
