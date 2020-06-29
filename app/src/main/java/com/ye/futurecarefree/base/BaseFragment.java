package com.ye.futurecarefree.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ye.futurecarefree.ui.activity.HomeActivity;
import com.ye.futurecarefree.utils.MyLog;

/**
 * Create Time : 2020-06-20
 * Author : WoDong
 * Desc :
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mPresenter = initPresenter();
        initView(view);
        initData(mContext);

        return view;
    }



    protected abstract void initView(View view);

    protected abstract void initData(Context mContext);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //只有在onViewCreate()回调的时候才会有Activity

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();
}
