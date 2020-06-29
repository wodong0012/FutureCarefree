package com.ye.futurecarefree.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ye.futurecarefree.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Time : 2020/6/17
 * Author : WoDong
 * Desc :
 */
public class DemandLvAdapter extends BaseAdapter {

    private String[] demandName = new String[]{"文件储存", "访问设备信息", "定位信息", "使用相机", "日历功能", "访问麦克风"};
    private String[] demandDesc = new String[]{"开启文件存储权限，方便您预览简历时保存至本地等功能", "开启访问设备信息，是用于判断用户身份，标识您是前程无忧用户"
            , "开启定位权限，方便您自动定位当前所在城市，查询附近工作等功能。未开启定位权限时，默认展示上海职位相关信息", "开启相机权限，方便您编辑简历时上传照片和在线面试时视频等功能",
            "开启日历权限，方便您将宣讲会等加入到您的日历提醒中，防止遗忘", "开启麦克风权限，方便您在线面试时可以直接语音沟通功能。"};
    private int[] demandRes = new int[]{R.drawable.privacy_file,
            R.drawable.privacy_equipment,
            R.drawable.privacy_position,
            R.drawable.privacy_camera,
            R.drawable.privacy_calendar,
            R.drawable.privacy_mike};

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DemandHolder demandHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.demand_lv_item, null);
            demandHolder = new DemandHolder(convertView);
            convertView.setTag(demandHolder);
        } else {
            demandHolder = (DemandHolder) convertView.getTag();
        }
        demandHolder.initData(position);
        return convertView;
    }

    class DemandHolder{
        ImageView demand_icon;
        TextView demand_name;
        TextView demand_desc;

        public DemandHolder(View view) {
            demand_icon = view.findViewById(R.id.demand_icon);
            demand_name = view.findViewById(R.id.demand_name);
            demand_desc = view.findViewById(R.id.demand_desc);
        }

        public void initData(int position) {
            demand_icon.setImageResource(demandRes[position]);
            demand_name.setText(demandName[position]);
            demand_desc.setText(demandDesc[position]);
        }
    }

    @Override

    public int getCount() {
        return demandRes.length;
    }

    @Override
    public Object getItem(int position) {
        return demandName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
