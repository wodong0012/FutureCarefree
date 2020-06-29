package com.ye.futurecarefree.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.base.BaseFragment;
import com.ye.futurecarefree.bean.ChoicenessPositionEntity;
import com.ye.futurecarefree.bean.HomepageTitleBannerEntity;
import com.ye.futurecarefree.constants.Constant;
import com.ye.futurecarefree.listener.HomepageTitleBannerVPItemListener;
import com.ye.futurecarefree.listener.SuspensionTitleListener;
import com.ye.futurecarefree.mvp.presenter.FragmentHomePresenter;
import com.ye.futurecarefree.mvp.view.IFragmentHomepageView;
import com.ye.futurecarefree.ui.activity.HomeChoseLocationActivity;
import com.ye.futurecarefree.ui.activity.HomeSearchActivity;
import com.ye.futurecarefree.ui.adapter.HomepageTitleBannerAdapter;
import com.ye.futurecarefree.ui.customUI.SuspensionTitleScrollView;
import com.ye.futurecarefree.utils.MyLog;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Create Time : 2020-06-20
 * Author : WoDong
 * Desc :
 */
public class HomepageHomeFragmentView extends BaseFragment<FragmentHomePresenter> implements IFragmentHomepageView, View.OnClickListener, ViewPager.OnPageChangeListener, HomepageTitleBannerVPItemListener, SuspensionTitleListener {


    private final Timer timer = new Timer();
    private TimerTask timerTask;
    private TextView mHomePage_location_tv;
    private LinearLayout mHomePage_search_post;
    private ViewPager mHomePage_banner_vp;
    private HomepageTitleBannerEntity mBannerEntity;
    private HomepageTitleBannerAdapter myAdapter;
    private int index = 0;
    private ImageView indicate1;
    private ImageView indicate2;
    private ImageView indicate3;
    private ViewPager mHomepage_between_banner_vp;
    private HomepageTitleBannerAdapter myAdapterTwo;
    private int indexBetween;
    private LinearLayout mHomepage_scroll_container;
    /**
     * Scrollview中悬浮标题栏上方控件的高度
     */
    private int mSuspensionTopSize;
    private LinearLayout mHomepage_suspension_titleOne;
    private LinearLayout mHomepage_suspension_titleTwo;
    private RelativeLayout mHomepage_suspension_content;
    private SuspensionTitleScrollView mScrollview;
    private LinearLayout mHomepage_choiceness_container;


    @Override
    protected void initData(Context mContext) {

    }

    @Override
    protected FragmentHomePresenter initPresenter() {
        return new FragmentHomePresenter(mContext, this);
    }


    @Override
    protected void initView(View view) {
        //主页ScrollView
        mScrollview = view.findViewById(R.id.homepage_scrollview);
        mScrollview.setOnScrollListener(this);

        //主页位置选择控件
        mHomePage_location_tv = view.findViewById(R.id.homePage_location_tv);
        //主页搜索框控件
        mHomePage_search_post = view.findViewById(R.id.homePage_search_post);
        mHomePage_location_tv.setOnClickListener(this);
        mHomePage_search_post.setOnClickListener(this);

        //头部广告页
        initTitleBanner(view);
        //快捷菜单
        initShortcutMenu(view);
        //自动滚动翻页
        initTimer();

        //悬浮标题栏上方的控件
        initSuspensionTitle(view);


        initChoicenessPosition(view);


    }

    /**
     * 精选职位的初始化
     *
     * @param view
     */
    private void initChoicenessPosition(View view) {
        mHomepage_choiceness_container = view.findViewById(R.id.homepage_choiceness_container);
        LayoutInflater inflater = LayoutInflater.from(mContext);

        //找定义的布局


        for (int i = 0; i < 15; i++) {
            MyLog.e(TAG,i%3+"    3的余数");
            MyLog.e(TAG,i%2+"    2的余数");
            String incType = "";
            switch ((i + 1) % 2) {
                case 0:
                    incType = "民营";
                    break;
                case 1:
                    incType = "国企";
                    break;
            }
            String schoolAge = "";
            switch ((i + 1) % 3) {
                case 0:
                    schoolAge = "中专";
                    break;
                case 1:
                    schoolAge = "大专";
                    break;
                case 2:
                    schoolAge = "本科";
                    break;
            }
            View choicenessPosition = inflater.inflate(R.layout.homepage_choiceness_container_item, null, false);

            //choiceness_content_positionName  岗位名称
            TextView choiceness_content_positionName = choicenessPosition.findViewById(R.id.choiceness_content_positionName);
            //choiceness_content_incType 公司性质： 民营、国企
            TextView choiceness_content_incType = choicenessPosition.findViewById(R.id.choiceness_content_incType);
            //choiceness_content_schoolage 学历: 目前只分三种 中专 ， 大专 ， 本科
            TextView choiceness_content_schoolage = choicenessPosition.findViewById(R.id.choiceness_content_schoolage);
            //choiceness_content_experience 工作经验
            TextView choiceness_content_experience = choicenessPosition.findViewById(R.id.choiceness_content_experience);
            //choiceness_content_incName  公司名称
            TextView choiceness_content_incName = choicenessPosition.findViewById(R.id.choiceness_content_incName);
            //choiceness_content_ask 申请岗位
            TextView choiceness_content_ask = choicenessPosition.findViewById(R.id.choiceness_content_ask);
            //choiceness_content_money 岗位工资
            TextView choiceness_content_money = choicenessPosition.findViewById(R.id.choiceness_content_money);
            //choiceness_content_location 工作地点
            TextView choiceness_content_location = choicenessPosition.findViewById(R.id.choiceness_content_location);


            choiceness_content_positionName.setText("Android工程师" + i);
            choiceness_content_incType.setText(incType);
            choiceness_content_schoolage.setText(schoolAge + "");
            choiceness_content_experience.setText((1 + i) + "年");
            choiceness_content_incName.setText("My" + (i + 1) + "公司");
            choiceness_content_money.setText(i + 1 * 10000d + "万/月");
            choiceness_content_location.setText("深圳");

            mHomepage_choiceness_container.addView(choicenessPosition);


        }

    }

    /**
     * 初始化ScrollView浮动标题栏
     *
     * @param view
     */
    private void initSuspensionTitle(View view) {
        mHomepage_scroll_container = view.findViewById(R.id.homepage_scroll_container);
        mHomepage_scroll_container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSuspensionTopSize = mHomepage_scroll_container.getHeight();
                MyLog.e(TAG, mSuspensionTopSize + "");
                mHomepage_scroll_container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        //底部精选职位Lv
        mHomepage_suspension_titleOne = view.findViewById(R.id.homepage_suspension_titleOne);
        mHomepage_suspension_titleTwo = view.findViewById(R.id.homepage_suspension_titleTwo);
        mHomepage_suspension_content = view.findViewById(R.id.homepage_suspension_content);
    }


    /**
     * 初始化访问快捷菜单栏
     *
     * @param view
     */
    private void initShortcutMenu(View view) {
/*        HomepageShortcutItem homepage_shortcut_lookMy = view.findViewById(R.id.homepage_shortcut_lookMy);
        HomepageShortcutItem homepage_shortcut_ask = view.findViewById(R.id.homepage_shortcut_ask;
        HomepageShortcutItem homepage_shortcut_nearby = view.findViewById(R.id.homepage_shortcut_nearby);
        HomepageShortcutItem homepage_shortcut_cultivate = view.findViewById(R.id.homepage_shortcut_cultivate);
        HomepageShortcutItem homepage_shortcut_school = view.findViewById(R.id.homepage_shortcut_school);*/
        view.findViewById(R.id.homepage_shortcut_lookMy).setOnClickListener(this);
        view.findViewById(R.id.homepage_shortcut_ask).setOnClickListener(this);
        view.findViewById(R.id.homepage_shortcut_nearby).setOnClickListener(this);
        view.findViewById(R.id.homepage_shortcut_cultivate).setOnClickListener(this);
        view.findViewById(R.id.homepage_shortcut_school).setOnClickListener(this);
    }

    /**
     * 头部广告初始化
     *
     * @param view
     */
    private void initTitleBanner(View view) {
        //头部广告Vp
        mHomePage_banner_vp = view.findViewById(R.id.homePage_banner_vp);
        myAdapter = new HomepageTitleBannerAdapter(mContext);
        mHomePage_banner_vp.addOnPageChangeListener(this);
        myAdapter.setHomepageTitleBannerVPItemListener(this);
        mHomePage_banner_vp.setAdapter(myAdapter);
        //中间广告Vp
        mHomepage_between_banner_vp = view.findViewById(R.id.homepage_between_banner_vp);
        myAdapterTwo = new HomepageTitleBannerAdapter(mContext);
        mHomepage_between_banner_vp.setAdapter(myAdapterTwo);
        mHomepage_between_banner_vp.addOnPageChangeListener(this);
        myAdapterTwo.setHomepageTitleBannerVPItemListener(this);

        indicate1 = view.findViewById(R.id.homepage_banner_indicate1);
        indicate2 = view.findViewById(R.id.homepage_banner_indicate2);
        indicate3 = view.findViewById(R.id.homepage_banner_indicate3);
        //请求网络去加载头部广告图片
        mPresenter.requestTitleBanner();
    }

    /**
     * 初始化指示器
     */
    private void initIndicate(int position) {
        indicate1.setBackgroundResource(android.R.drawable.presence_invisible);
        indicate2.setBackgroundResource(android.R.drawable.presence_invisible);
        indicate3.setBackgroundResource(android.R.drawable.presence_invisible);
        switch (position) {
            case 0:
                indicate1.setBackgroundResource(android.R.drawable.presence_online);
                break;
            case 1:
                indicate2.setBackgroundResource(android.R.drawable.presence_online);
                break;
            case 2:
                indicate3.setBackgroundResource(android.R.drawable.presence_online);
                break;
        }
    }

    /**
     * 头部广告循环播放
     */
    private void initTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(() -> {
                    index = mHomePage_banner_vp.getCurrentItem();
                    indexBetween = mHomePage_banner_vp.getCurrentItem();

                    if (++index == 3) {
                        index = 0;
                    }
                    if (++indexBetween == 3) {
                        indexBetween = 0;
                    }
//                        initIndicate(index);
                    mHomePage_banner_vp.setCurrentItem(index);
                    mHomepage_between_banner_vp.setCurrentItem(indexBetween);

                });
            }
        };
        timer.schedule(timerTask, 2000, 2000);
    }


    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage_home;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.homePage_location_tv:
                // TODO 页面跳转动画不够完美
                Intent locationIntent = new Intent(mContext, HomeChoseLocationActivity.class);
//                startActivityForResult(locationIntent, Constant.HOMEPAGE_LOCATION_REQUEST_CODE, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                startActivityForResult(locationIntent, Constant.HOMEPAGE_LOCATION_REQUEST_CODE);
                getActivity().overridePendingTransition(R.anim.homepage_location_open_enter, R.anim.homepage_location_open_exit);
                break;
            case R.id.homePage_search_post:
                Intent searchIntent = new Intent(mContext, HomeSearchActivity.class);
                startActivity(searchIntent);
                break;
        }
    }

    @Override
    public void responseMessage(int msg, Object obj) {
        switch (msg) {
            case Constant.RESPONSE_BANNER:
                if (obj != null) {
                    mBannerEntity = (HomepageTitleBannerEntity) obj;
                    List<String> banners = mBannerEntity.getBanners();
                    myAdapter.setData(banners);
                    myAdapterTwo.setData(banners);
                }
                myAdapter.notifyDataSetChanged();
                myAdapterTwo.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        MyLog.e(TAG,position+"onPageScrolled");
        initIndicate(mHomePage_banner_vp.getCurrentItem());
    }

    @Override
    public void onPageSelected(int position) {
//        MyLog.e(TAG,position+"onPageSelected");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
//                MyLog.e(TAG,mHomePage_banner_vp.getCurrentItem()+"SCROLL_STATE_IDLE");
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
//                MyLog.e(TAG,mHomePage_banner_vp.getCurrentItem()+"SCROLL_STATE_DRAGGING");
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
//                MyLog.e(TAG,mHomePage_banner_vp.getCurrentItem()+"SCROLL_STATE_SETTLING");
                break;
        }
    }


    /**
     * 头部广告条目被点击的回调
     *
     * @param position 被点击条目的position
     */
    @Override
    public void onVpItemClick(int position) {
        Toast.makeText(mContext, "头部广告条目 :" + (position + 1) + "  被被点击了", Toast.LENGTH_SHORT).show();
    }


    /**
     * 主页面ScrollView滑动获取Y值的回调
     *
     * @param scrollY
     */
    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= mSuspensionTopSize) {
            /*
                mHomepage_suspension_titleOne;
                mHomepage_suspension_titleTwo;
                mHomepage_suspension_content;
            */
            if (mHomepage_suspension_content.getParent() != mHomepage_suspension_titleTwo) {
                mScrollview.scrollTo(0, mSuspensionTopSize);
                mHomepage_suspension_titleOne.removeView(mHomepage_suspension_content);
                mHomepage_suspension_titleTwo.addView(mHomepage_suspension_content);
            }
        } else {
            if (mHomepage_suspension_content.getParent() != mHomepage_suspension_titleOne) {
                mHomepage_suspension_titleTwo.removeView(mHomepage_suspension_content);
                mHomepage_suspension_titleOne.addView(mHomepage_suspension_content);
            }
        }
    }
}





