package com.omninos.chroeslammer.Interface;

import android.telecom.Call;

import com.omninos.chroeslammer.Models.GetProfile_Response_Model;
import com.omninos.chroeslammer.Models.Job_List_Response_Model;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by sandeep on 24-05-2017.
 */

public interface Get_JobList_Api
{



    @POST("/api/Coreslamer/get_job_list")
    void getList(Callback<Job_List_Response_Model> jobListResponseModelCallback);


   /* @FormUrlEncoded
    @POST("/api/Coreslamer/get_job_list")
    public void getJobList(
            Callback<Job_List_Response_Model> respponse);*/
}
