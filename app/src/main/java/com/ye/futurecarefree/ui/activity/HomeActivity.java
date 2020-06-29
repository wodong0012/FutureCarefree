package com.ye.futurecarefree.ui.activity;

import android.Manifest;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.base.BaseActivity;
import com.ye.futurecarefree.base.BasePresenter;
import com.ye.futurecarefree.listener.PermissionListener;
import com.ye.futurecarefree.ui.customUI.HomeTableBarLinearLayout;
import com.ye.futurecarefree.ui.fragment.HomepageEduFragment;
import com.ye.futurecarefree.ui.fragment.HomepageHomeFragmentView;
import com.ye.futurecarefree.ui.fragment.HomepageMessageFragment;
import com.ye.futurecarefree.ui.fragment.HomepageMyFragment;
import com.ye.futurecarefree.ui.fragment.HomepageResumeFragment;
import com.ye.futurecarefree.utils.MyLog;

import java.util.List;

/**
 * 主页面
 */
public class HomeActivity extends BaseActivity implements PermissionListener, View.OnClickListener {

    //申请两个权限，录音和文件读写
    //需要的权限
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.READ_EXTERNAL_STORAGE};
    private HomeTableBarLinearLayout home_table_bar_home;
    private HomeTableBarLinearLayout home_table_bar_message;
    private HomeTableBarLinearLayout home_table_bar_edu;
    private HomeTableBarLinearLayout home_table_bar_resume;
    private HomeTableBarLinearLayout home_table_bar_my;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;

//    String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.READ_EXTERNAL_STORAGE};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        requestRuntimePermission(permissions, this);
        //获取FragmentManager
        fm = getSupportFragmentManager();
        initBottomBar();
        //默认Home页面是被点击的
        home_table_bar_home.performClick();


    }

    private void initBottomBar() {
        //主页
        home_table_bar_home = findViewById(R.id.home_table_bar_home);
        home_table_bar_home.setOnClickListener(this);
        //消息
        home_table_bar_message = findViewById(R.id.home_table_bar_message);
        home_table_bar_message.setOnClickListener(this);
        //学习
        home_table_bar_edu = findViewById(R.id.home_table_bar_edu);
        home_table_bar_edu.setOnClickListener(this);
        //简历
        home_table_bar_resume = findViewById(R.id.home_table_bar_resume);
        home_table_bar_resume.setOnClickListener(this);
        //我
        home_table_bar_my = findViewById(R.id.home_table_bar_my);
        home_table_bar_my.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        setBarBtnStatus(id);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (id) {
            case R.id.home_table_bar_home:
                fragmentTransaction.replace(R.id.home_frameLayout, new HomepageHomeFragmentView());
                break;
            case R.id.home_table_bar_message:
                fragmentTransaction.replace(R.id.home_frameLayout, new HomepageMessageFragment());
                break;
            case R.id.home_table_bar_edu:
                fragmentTransaction.replace(R.id.home_frameLayout, new HomepageEduFragment());
                break;
            case R.id.home_table_bar_resume:
                fragmentTransaction.replace(R.id.home_frameLayout, new HomepageResumeFragment());
                break;
            case R.id.home_table_bar_my:
                fragmentTransaction.replace(R.id.home_frameLayout, new HomepageMyFragment());
                break;
        }
        fragmentTransaction.commit();
    }

    public void setBarBtnStatus(int resId) {
        home_table_bar_home.setBtnSelect(R.id.home_table_bar_home == resId);
        home_table_bar_message.setBtnSelect(R.id.home_table_bar_message == resId);
        home_table_bar_edu.setBtnSelect(R.id.home_table_bar_edu == resId);
        home_table_bar_resume.setBtnSelect(R.id.home_table_bar_resume == resId);
        home_table_bar_my.setBtnSelect(R.id.home_table_bar_my == resId);
    }

    //权限申请成功回调
    @Override
    public void onGranted() {
        MyLog.e(TAG, "获取权限成功");
    }
    //权限申请失败回调

    @Override
    public void onDenied(List<String> deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            MyLog.e(TAG, deniedPermission);
        }
        MyLog.e(TAG, "失败");
    }

}

