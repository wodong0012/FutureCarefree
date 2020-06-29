package com.ye.futurecarefree.ui.customUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.utils.MyLog;

/**
 * Create Time : 2020-06-25
 * Author : WoDong
 * Desc :
 */
public class HomepageVocationItem extends LinearLayout {

    private TextView desc_tv;
    private TextView title_tv;

    public HomepageVocationItem(Context context) {
        super(context,null);
    }

    public HomepageVocationItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /*
        <attr name="title" format="string" />
        <attr name="desc" format="string" />
        <attr name="background" format="reference" />
        */
        String title = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "title");
        String desc = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "desc");
        int background = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "background", -1);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HomepageVocationItem);
        Drawable drawable = typedArray.getDrawable(R.styleable.HomepageVocationItem_background);
        setBackground(drawable);


        View view = LayoutInflater.from(context).inflate(R.layout.homepage_vocation_item, this);
        initView(view);
        title_tv.setText(title);
        desc_tv.setText(desc);
    }

    private void initView(View view) {
        title_tv = view.findViewById(R.id.homepage_vocation_title);
        desc_tv = view.findViewById(R.id.homepage_vocation_desc);
    }
}
