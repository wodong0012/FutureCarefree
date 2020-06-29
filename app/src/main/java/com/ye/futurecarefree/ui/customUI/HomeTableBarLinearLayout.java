package com.ye.futurecarefree.ui.customUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.ye.futurecarefree.R;
import com.ye.futurecarefree.utils.MyLog;

/**
 * Create Time : 2020-06-20
 * Author : WoDong
 * Desc :
 */
public class HomeTableBarLinearLayout extends LinearLayout {
    private String TAG = getClass().getSimpleName();
    private ImageView img;
    private TextView name;


    public HomeTableBarLinearLayout(Context context) {
        super(context, null);
    }

    public HomeTableBarLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.home_table_bar, this);
        img = view.findViewById(R.id.home_table_edu_img);
        name = view.findViewById(R.id.home_table_edu_title);


        int btnType = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "btnType", -1);

        initUi(btnType);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HomeTableBar);

    }

    private void initUi(int btnType) {
        switch (btnType) {
            case 1:
                img.setBackgroundResource(R.drawable.table_home_select);
                name.setText("首页");
                break;
            case 2:
                img.setBackgroundResource(R.drawable.table_message_select);
                name.setText("消息");
                break;
            case 3:
                img.setBackgroundResource(R.drawable.table_edu_select);
                name.setText("学习");
                break;
            case 4:
                img.setBackgroundResource(R.drawable.table_resume_select);
                name.setText("简历");
                break;
            case 5:
                img.setBackgroundResource(R.drawable.table_my_select);
                name.setText("我的");
                break;
        }
    }


    public void setBtnSelect(boolean status){
        img.setSelected(status);
        if (status) {
            name.setTextColor(0xFFFF6E37);
        } else {
            name.setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

}
