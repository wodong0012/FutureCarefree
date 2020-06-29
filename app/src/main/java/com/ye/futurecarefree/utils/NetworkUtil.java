package com.ye.futurecarefree.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * 网络状态的工具类
 */
public class NetworkUtil {

    /**
     * 获取网络的状态
     * @param context 传入上下文
     * @return -1 : 当前没有网络连接，1 ：当前网路状态是MOBILE，2 ：当前网络是WIFI
     */
    public static int getNetworkIsStatus(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = connectivity.getActiveNetwork();
        if (activeNetwork == null) {
            //没有网络连接
            return -1;
        } else {
            NetworkInfo networkStatus = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            networkStatus = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkStatus != null && networkStatus.isConnected()) {
                //当前网络状态是WIFI
                return 2;
            }
            if (networkStatus != null && networkStatus.isConnected()) {
                //当前网络状态是MOBILE
                return 1;
            }
        }
        return -1;
    }

}
