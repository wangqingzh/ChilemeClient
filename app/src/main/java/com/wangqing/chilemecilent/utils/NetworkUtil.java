package com.wangqing.chilemecilent.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 网络工具包
 */
public class NetworkUtil {
    public static final String TAG = "NetworkUtil";


    /**
     * 是否已经连接网络
     *
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Log.d(TAG, "connectivitymanager is not null");
            //获取代表联网对象的NetworkInfo对象
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.d(TAG, "activityNetworkInfo is not null and is connected");
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                    //获取当前的网络连接是否可用
                    Log.d(TAG, "is mobile or wifi");
                return true;
            }
            return false;
        }
        return false;
    }

}
