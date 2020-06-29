package com.ye.futurecarefree.utils;

import android.util.Log;

/**
 * Log的工具类 目前只是简单实现Log
 */
public class MyLog {
    /**
     * 所有Log的总开关
     */
    public static boolean logStatus = true;

    public static void v(String tag, String message) {
        if (logStatus) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (logStatus) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (logStatus) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (logStatus) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (logStatus) {
            Log.e(tag, message);
        }
    }


}
