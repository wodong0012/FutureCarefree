package com.ye.futurecarefree.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ye.futurecarefree.listener.PermissionListener;
import com.ye.futurecarefree.utils.MyLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;
    public static List<AppCompatActivity> mActivitys = new LinkedList<>();
    protected String TAG = this.getClass().getSimpleName();
    protected PermissionListener mPermissionListener;
    private final int mRequestCode = 100;//权限请求码
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的字体为蓝色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        setScreenRotate(true);
        //初始化的时候将当前布局添加到集合中
        mActivitys.add(this);

        MyLog.e(TAG,"当前的Activity是:"+this.getClass().getSimpleName());

        setContentView(getLayoutId());

//        makeStatusBarTransparent(this);
        mPresenter =  initPresenter();
        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //布局销毁时将布局从集合中移除
        mActivitys.remove(this);
    }

    //设置状态栏为透明
    public static void makeStatusBarTransparent(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 退出应用的方法
     */
    public void exitApp(){
        Iterator<AppCompatActivity> iterator = mActivitys.iterator();
        while (iterator.hasNext()) {
            AppCompatActivity next = iterator.next();
            next.finish();
        }
    }

    /**
     * 申请运行时权限
     * @param permissions 需要申请的权限集合
     * @param permissionListener 申请之后的回调
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限

            //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
            List<String> mPermissionList = new ArrayList<>();


            mPermissionListener = permissionListener;
            mPermissionList.clear();//清空没有通过的权限
            //逐个判断你要的权限是否已经通过
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);//添加还未授予的权限
                }
            }

            //申请权限
            if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
                ActivityCompat.requestPermissions(this, permissions, mRequestCode);
            } else {
                //说明权限都已经通过，可以做你想做的事情去
                mPermissionListener.onGranted();
            }

//            List<String> pers = new ArrayList<>();
//
//
//            if (permissions != null && permissions.length != 0) {
//                for (String permission : permissions) {
//                    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                        pers.add(permission);
//                    }
//                }
//            }
//            if (!pers.isEmpty()) {
//                ActivityCompat.requestPermissions(this,pers.toArray(new String[pers.size()]),1);
//            } else {
//                mPermissionListener.onGranted();
//            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0) {
//                    List<String> deniedPermission = new ArrayList<>();
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                            deniedPermission.add(permissions[i]);
//                        }
//                    }
//                    if (deniedPermission.isEmpty()) {
//                        //为空
//                        mPermissionListener.onGranted();
//                    } else {
//                        //不为空
//                        mPermissionListener.onDenied(deniedPermission);
//                    }
//                }
//                break;
//        }

        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            } else {
                //全部权限通过，可以进行下一步操作。。。

            }
        }

    }

    /**
     * 不再提示权限时的展示对话框
     */
    AlertDialog mPermissionDialog;
    String mPackName = "com.ye.futurecarefree";

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();

                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //关闭对话框
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    /**
     * 子类调用此方法来填充当前的布局
     * @return 返回填充布局的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Presenter
     */
    protected abstract P  initPresenter();

    /**
     * 子类初始化数据
     */
    protected abstract void initData();

    /**
     * 子类初始化布局
     */
    protected abstract void initView();

    /**
     * 锁定界面的横竖屏
     * @param rotate 传入true为竖屏，false为横屏
     */
    private void setScreenRotate(boolean rotate) {
        if (rotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected String getTopActivity(){
        ActivityManager activityManage = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManage.getRunningTasks(1);
        if (runningTasks!= null){
            String topActivity = runningTasks.get(0).topActivity.toString();
            return topActivity;
        }
        return "";
    }

}
