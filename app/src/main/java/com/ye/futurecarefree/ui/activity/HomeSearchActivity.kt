package com.ye.futurecarefree.ui.activity

import com.ye.futurecarefree.R
import com.ye.futurecarefree.base.BaseActivity
import com.ye.futurecarefree.mvp.presenter.FragmentHomePresenter
import com.ye.futurecarefree.mvp.view.IFragmentHomepageView

/**
 * Create Time : 2020-06-23
 * Author : WoDong
 * Desc : Fragment顶部搜索框页面
 */
class HomeSearchActivity :BaseActivity<FragmentHomePresenter>() ,IFragmentHomepageView {
    override fun getLayoutId() = R.layout.activity_homepage_search

    override fun initPresenter() = FragmentHomePresenter(this,this)

    override fun initData() {

    }

    override fun initView() {

    }

    override fun responseMessage(msg: Int, obj: Any?) {
    }

}