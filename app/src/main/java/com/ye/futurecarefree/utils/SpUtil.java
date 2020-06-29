package com.ye.futurecarefree.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ye.futurecarefree.constants.Constant;

public class SpUtil {



    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constant.SP.SP_NAME,Context.MODE_PRIVATE);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(key, true);
    }
}
