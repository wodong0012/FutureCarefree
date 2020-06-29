package com.ye.futurecarefree.ui.activity

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.ye.futurecarefree.R
import kotlinx.android.synthetic.main.activity_chose_location.*


/**
*
* 页面实现思路：
*      获取到所有的城市以及热门城市数据
*      获取城市第一个字的首字母在按A-Z的顺序排序，城市列表缩写ListView第一项为热门城市
*      城市选择页用ScrollView当容器 在代码中动态添加城市选择元素
*              第一个元素动态获取当前设备所在位置来判断地点
*             元素需要设置上下左右边距以及填充颜色
*
*/
class HomeChoseLocationActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        // 设置activity的窗口属性为contentFeature,即可使用切换动画
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        val explode =
//            TransitionInflater.from(this).inflateTransition(android.R.transition.explode)
//        // 此处获取了系统内置的explode动画效果设置给了activity的窗口对象
//        // 此处获取了系统内置的explode动画效果设置给了activity的窗口对象
//        window.enterTransition = explode
        setContentView(R.layout.activity_chose_location)

        initView()

    }


    private fun initView() {
        //返回按钮
        homePage_location_back.setOnClickListener{
            finish()
//            overridePendingTransition(R.anim.homepage_location_close_enter,0)
        }

//        location_acronym_city_list

    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.homepage_location_close_enter,0)
//        super.onBackPressed()
    }

}
