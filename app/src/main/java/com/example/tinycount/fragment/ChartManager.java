package com.example.tinycount.fragment;

import android.app.Activity;
import android.graphics.Color;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.db.AccountDao;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class ChartManager {
    private Activity mContext;
    //颜色列表
    ArrayList<Integer> mOriginColors = new ArrayList<Integer>();

    public ChartManager(Activity mContext) {
        this.mContext = mContext;
        mOriginColors.add(Color.parseColor("#59EA3A"));
        mOriginColors.add(Color.parseColor("#FFFA40"));
        mOriginColors.add(Color.parseColor("#E238A7"));

        mOriginColors.add(Color.parseColor("#8DB42D"));
        mOriginColors.add(Color.parseColor("#3DA028"));
        mOriginColors.add(Color.parseColor("#BFBC30"));
        mOriginColors.add(Color.parseColor("#94256D"));

        mOriginColors.add(Color.parseColor("#66C3E3"));
        mOriginColors.add(Color.parseColor("#39B8E3"));
        mOriginColors.add(Color.parseColor("#0095C6"));
        mOriginColors.add(Color.parseColor("#257995"));
        mOriginColors.add(Color.parseColor("#006181"));
    }

    public void showPieChartAccount(PieChart pieChart, String date){
        //数据
        AccountApplication app = (AccountApplication)(mContext.getApplication());
        AccountDao dbManager = app.getDatabaseManager();


    }
}
