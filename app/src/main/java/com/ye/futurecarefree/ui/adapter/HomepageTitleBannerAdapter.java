package com.ye.futurecarefree.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ye.futurecarefree.constants.NetworkConstants;
import com.ye.futurecarefree.listener.HomepageTitleBannerVPItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Time : 2020-06-23
 * Author : WoDong
 * Desc :
 */
public class HomepageTitleBannerAdapter extends PagerAdapter {

    private HomepageTitleBannerVPItemListener mListener;

    private List<ImageView> mBannerImgs = new ArrayList<>();
    private Context mContext;

    public HomepageTitleBannerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> banners) {
        for (int a = 0; a < banners.size(); a++) {
            String url = NetworkConstants.TITLE_BANNER_URL + banners.get(a);
            ImageView img = new ImageView(mContext);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(url).into(img);
            mBannerImgs.add(img);
        }
    }

    public void setHomepageTitleBannerVPItemListener(HomepageTitleBannerVPItemListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        if (mBannerImgs.size() == 0) {
            return 0;
        } else {
            return mBannerImgs.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mBannerImgs.get(position));

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = mBannerImgs.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置监听器通知activity被点击的条目
                if (mListener != null) {
                    mListener.onVpItemClick(position);
                }
            }
        });
        container.addView(imageView);
        return mBannerImgs.get(position);

    }
}
