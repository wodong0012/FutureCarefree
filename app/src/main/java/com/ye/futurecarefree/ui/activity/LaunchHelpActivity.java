package com.ye.futurecarefree.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.base.BaseActivity;
import com.ye.futurecarefree.base.BasePresenter;

/**
 * Create Time : 2020-06-17
 * Author : WoDong
 * Desc :
 */
public class LaunchHelpActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_launchhelp;
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
        findViewById(R.id.btn_startAppHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchHelpActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
