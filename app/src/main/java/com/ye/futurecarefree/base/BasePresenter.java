package com.ye.futurecarefree.base;

import android.content.Context;

import com.ye.futurecarefree.http.retrofit.ApiRetrofit;
import com.ye.futurecarefree.http.retrofit.LaunchService;
import com.ye.futurecarefree.mvp.view.IBaseView;

/**
 * Presenter基类
 *
 * @param <V> Presenter持有的View
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected static final String TAG = BasePresenter.class.getSimpleName();

    /**
     * Retrofit网络请求对象
     */
     protected ApiRetrofit mRetrofit = ApiRetrofit.getInstance();


    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 当前的View
     */
    protected V mView;


    public BasePresenter(Context mContext, V mView) {
        this.mContext = mContext;
        this.mView = mView;
    }
}
