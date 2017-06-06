package com.omninos.chroeslammer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 19-05-2017.
 */

public class GetProfile_Response_Model
{
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_detail")
    @Expose
    private Get_Profile_Result get_profile_result;

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

    public Get_Profile_Result get_profile_result() {
        return get_profile_result;
    }

    public void setGet_profile_result(Get_Profile_Result get_profile_result) {
        this.get_profile_result = get_profile_result;
    }
}
