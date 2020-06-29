package com.ye.futurecarefree.mvp.presenter

import android.content.Context
import com.ye.futurecarefree.base.BasePresenter
import com.ye.futurecarefree.http.retrofit.LaunchService
import com.ye.futurecarefree.mvp.view.ILaunchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LaunchPresenter(context: Context,view: ILaunchView) : BasePresenter<ILaunchView>(context,view) {
    val mLaunchService = mRetrofit.getApiService(LaunchService::class.java)
    /**
     * 获取启动页面的广告
     */
    fun getLaunchPoster(){
        //TODO 这里需要先对网络进行判断，是WIFI就下载，并保存，如果是流量直接return
        mLaunchService.launchPoster.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->

                if (t != null) {
                    val byteStream = t.byteStream()
                    mView.LaunchPosterResponse(byteStream)
                }
            }
    }

}