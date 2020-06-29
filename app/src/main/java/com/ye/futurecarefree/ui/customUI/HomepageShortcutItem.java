package com.ye.futurecarefree.ui.customUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ye.futurecarefree.R;

/**
 * Create Time : 2020-06-25
 * Author : WoDong
 * Desc :
 */
public class HomepageShortcutItem extends LinearLayout {

    private ImageView img;
    private TextView name;
    private int itemType;

    public HomepageShortcutItem(Context context) {
        super(context, null);
    }

    public HomepageShortcutItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.homepage_shortcut_item, this);
        itemType = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "shortcutType", -1);
        initView(view);
    }

    private void initView(View view) {
        img = view.findViewById(R.id.homepage_shortcut_img);
        name = view.findViewById(R.id.homepage_shortcut_name);
        if (itemType != -1) {
            switch (itemType) {
                case 0:
                    img.setBackgroundResource(R.drawable.home_main_who);
                    name.setText("谁看过我");
                    break;
                case 1:
                    img.setBackgroundResource(R.drawable.home_main_record);
                    name.setText("申请记录");
                    break;
                case 2:
                    img.setBackgroundResource(R.drawable.home_main_beside);
                    name.setText("附近职位");
                    break;
                case 3:
                    img.setBackgroundResource(R.drawable.home_main_strategy);
                    name.setText("职场培训");
                    break;
                case 4:
                    img.setBackgroundResource(R.drawable.home_main_school);
                    name.setText("校园招聘");
                    break;
            }
        }
    }
}
