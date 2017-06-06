package com.omninos.chroeslammer.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sandeep on 24-05-2017.
 */

public class Check_Internet_Connection
{
    private static ConnectivityManager connectivityManager;
    private static NetworkInfo networkInfo;
    private static Context context;
    private static boolean check;
    //private static ConnectivityManager connectivityManager;
 //   private Context context;
   // private ConnectivityManager connectivityManager;
  //  private NetworkInfo networkInfo;
   // private boolean check = false;

    public Check_Internet_Connection(Context context) {
        this.context = context;
        check = false;
    }

    public static boolean checkIntenetConnection()
    {
        connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true )
        {
            check = true;
        }
        return check;
    }



}
