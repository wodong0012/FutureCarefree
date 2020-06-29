package com.ye.futurecarefree.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.ye.futurecarefree.R
import com.ye.futurecarefree.base.BaseActivity
import com.ye.futurecarefree.constants.Constant
import com.ye.futurecarefree.mvp.presenter.LaunchPresenter
import com.ye.futurecarefree.mvp.view.ILaunchView
import com.ye.futurecarefree.utils.NetworkUtil
import com.ye.futurecarefree.utils.SpUtil
import kotlinx.android.synthetic.main.activity_launch.*
import java.io.InputStream
import kotlin.concurrent.thread

/**
 * 启动页面
 *  需要判断是否是第一次启动，是需要请求网络去下载图片资源
 */
class LaunchActivity : BaseActivity<LaunchPresenter>(), ILaunchView, View.OnClickListener {
    private lateinit var mContext: Context
    private var startTime: Long = 0
    private var isFirstLaunch:Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun initPresenter(): LaunchPresenter {
        mContext = this
        return LaunchPresenter(mContext, this)
    }

    override fun initData() {
    }

    @SuppressLint("NewApi")
    override fun initView() {

        //开发时期直接启动home页面
        startHome(0)



        //判断 是否第一次启动
        isFirstLaunch = SpUtil.getBoolean(mContext, Constant.SP.SP_IS_FIRST_LAUNCH)
        launch_break.setOnClickListener(this)
        if (isFirstLaunch) {
            thread {
                //让程序睡眠2s
                SystemClock.sleep(2000)
                val topActivity = topActivity
                if (topActivity.contains("LaunchActivity")) {
                    //当前Activity处于栈顶时LaunchActivity才自动跳转
                    //跳转权限声明页面
                    val intent = Intent(mContext, DemandActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        } else {

            startTime = System.currentTimeMillis()
            //先判断网络状态 不是WIFI 是WIFI
            val networkIsStatus = NetworkUtil.getNetworkIsStatus(mContext)
            when (networkIsStatus) {
                1 -> {
                    //TODO 需要判断是否有缓存 有就去本地加载缓存
                    //流量  不访问网络下载 2s之后直接进入主界面
                    startHome(startTime)
                }
                2 -> {
                    //WIFI 访问网络下载资源
                    mPresenter.getLaunchPoster()
                }
            }
            thread {
                startHome(startTime)
            }


            launch_img.setOnClickListener {
                //TODO 当点击广告时 去访问对应的页面
                Toast.makeText(mContext, "广告也被点击了！", Toast.LENGTH_SHORT).show()
            }

        }
//                startHome(startTime)


    }

    override fun LaunchPosterResponse(byteStream: InputStream) {

        //如果是wifi状态就去网上获取资源，流量后台则不会回调此方法
        launch_img.setImageBitmap(BitmapFactory.decodeStream(byteStream))

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.launch_break -> {//跳过按钮
                if (isFirstLaunch) {
                    //跳转权限声明页面
                    val intent = Intent(mContext, DemandActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    startHome(0)
                }
            }
        }

    }

    /**
     * 启动主页面，并关闭当前页面
     */
    @SuppressLint("NewApi")
    private fun startHome(startTime: Long) {
        if (startTime != 0L) {
            if (!topActivity.contains("LaunchActivity")) return
            val endTime = System.currentTimeMillis()
            if (endTime - startTime < 5000) {
                //让程序睡眠到对应时间
                SystemClock.sleep(5000 - (endTime - startTime))
            }
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
