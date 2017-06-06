package com.omninos.chroeslammer.Helper;

import retrofit.RestAdapter;

/**
 * Created by sandeep on 17-05-2017.
 */

public class Retrofit_Instance
{
    public static final String BASE_URL = "http://omninos.com/common/index.php";
    public static RestAdapter retrofit = null;




    public static RestAdapter getRetrofit() {
        if (retrofit==null) {
            retrofit = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
