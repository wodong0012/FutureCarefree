package com.ye.futurecarefree.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.base.BaseActivity;
import com.ye.futurecarefree.base.BasePresenter;
import com.ye.futurecarefree.constants.Constant;
import com.ye.futurecarefree.ui.adapter.DemandLvAdapter;
import com.ye.futurecarefree.utils.SpUtil;

/**
 * 首次启动需要的权限说明页
 */
public class DemandActivity extends BaseActivity {
    private ListView mDemand_lv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }


    @Override
    protected BasePresenter initPresenter() {
        //此页面不需要Presenter
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mDemand_lv = findViewById(R.id.demand_lv);
        DemandLvAdapter demandLvAdapter = new DemandLvAdapter();
        mDemand_lv.setAdapter(demandLvAdapter);

        findViewById(R.id.demandKnow_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemandActivity.this, LaunchHelpActivity.class);
                startActivity(intent);
                SpUtil.putBoolean(DemandActivity.this, Constant.SP.SP_IS_FIRST_LAUNCH, false);
                finish();
            }
        });

    }
}
