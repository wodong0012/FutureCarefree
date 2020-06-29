package com.ye.futurecarefree.mvp.presenter

import android.content.Context
import com.ye.futurecarefree.base.BasePresenter
import com.ye.futurecarefree.constants.Constant
import com.ye.futurecarefree.http.retrofit.HomepageService
import com.ye.futurecarefree.mvp.view.IFragmentHomepageView
import com.ye.futurecarefree.utils.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create Time : 2020-06-21
 * Author : WoDong
* Desc :
*/
class FragmentHomePresenter(context:Context,view: IFragmentHomepageView) : BasePresenter<IFragmentHomepageView>(context, view) {
    val homepageService = mRetrofit.getApiService(HomepageService::class.java)
    //请求网络加载头部广告图片

    fun requestTitleBanner() {
        homepageService.getTitleBanner("title_banner.json").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{body ->
                MyLog.e(javaClass.simpleName,body.toString())
                mView.responseMessage(Constant.RESPONSE_BANNER,body)
            }
    }
}


