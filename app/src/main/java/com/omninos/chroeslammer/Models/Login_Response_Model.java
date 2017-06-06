package com.omninos.chroeslammer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 19-05-2017.
 */

public class Login_Response_Model
{
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Login_Result> result = null;

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

    public List<Login_Result> getResult() {
        return result;
    }

    public void setResult(List<Login_Result> result) {

    }

}
