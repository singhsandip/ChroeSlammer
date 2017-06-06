package com.omninos.chroeslammer.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.omninos.chroeslammer.Models.JobList;

import java.util.List;

/**
 * Created by sandeep on 17-05-2017.
 */

public class SharedPrefManager
{
    public static final String SHARED_PREF_NAME = "FCMSharedPref";
    public static final String USER_ID = "user_id";
    public static final String TAG_TOKEN = "tagtoken";
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

  /*  private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;*/

   private static SharedPrefManager mInstance;
    private static Context mCtx;


    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }

    public void saveJobList(List<JobList> list){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(Constants.JOBLIST,Objec)
        //editor.putString(Constants.JOBLIST, String.valueOf(list));
        editor.apply();
    }

   /* public List<JobList> getJobList(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.get
        return  sharedPreferences.getString(Constants.JOBLIST, null);
    }*/


    public void setUserId(String userId)
{
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(USER_ID, userId);
    editor.apply();
}

    public String getUserId()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(USER_ID, "");
    }

    public void setJobTittle(String jobTittle)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.JOBTITLE, jobTittle);
        editor.apply();
    }

    public String getJobTitrle()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.JOBTITLE, "");
    }

    public void setCategory(String cAtegory)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.CATEGORY, cAtegory);

        editor.apply();
    }

    public String getCategory()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.CATEGORY, "");
    }

    public void setUserName(String userName)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USERNAME, userName);
        editor.apply();
    }

    public String getUserName()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.USERNAME, "");
    }
    public void setCreatedDate(String date)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.CREATED_DATE, date);
        editor.apply();
    }

    public String getCreatedDate()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.CREATED_DATE, "");
    }

    public void setIMage(String image)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.IMAGE, image);
        editor.apply();
    }

    public String getImage()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.IMAGE, "");
    }
  /*  public void setUserName(String username)
    {
        editor = sharedPreferences.edit();
        editor.putString(Constants.USERNAME,username);
        editor.apply();
    }

    public String getUserName()
    {
        sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Constants.USERNAME, "");

    }
*/

}
