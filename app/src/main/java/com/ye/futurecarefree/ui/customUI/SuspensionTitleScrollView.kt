package com.ye.futurecarefree.ui.customUI

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.ye.futurecarefree.listener.SuspensionTitleListener
import com.ye.futurecarefree.utils.MyLog

/**
 * Create Time : 2020-06-28
 * Author : WoDong
 * Desc :
 */
class SuspensionTitleScrollView : ScrollView {


    private var lastScrollY:Int = 0
    private lateinit var mScrollListener:SuspensionTitleListener
    private val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            //获取当前滑动的Y值
            var scrollY:Int = this@SuspensionTitleScrollView.scrollY
            MyLog.e(javaClass.simpleName,"scrollY : $scrollY")
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY
                sendMessageDelayed(this.obtainMessage(),5)
            }
            if (mScrollListener != null) {
                mScrollListener.onScroll(scrollY)
            }
        }
    }


    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (mScrollListener != null) {
            lastScrollY = scrollY
            mScrollListener.onScroll(lastScrollY)
        }
        when (ev?.action) {
            //手指抬起之后隔20毫秒给handler发消息获取滑动的Y值
            MotionEvent.ACTION_UP -> handler.sendMessageDelayed(handler.obtainMessage(),20)
        }


        return super.onTouchEvent(ev)
    }

    fun setOnScrollListener(listener:SuspensionTitleListener) {
        this.mScrollListener = listener
    }

}