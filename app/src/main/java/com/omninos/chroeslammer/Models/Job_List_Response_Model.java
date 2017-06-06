package com.omninos.chroeslammer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 24-05-2017.
 */

public class Job_List_Response_Model
{
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("job_list")
    @Expose
    private List<JobList> jobList = null;

    public Job_List_Response_Model(List<JobList> jobList) {
        this.jobList = jobList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JobList> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobList> jobList) {
        this.jobList = jobList;
    }
}
