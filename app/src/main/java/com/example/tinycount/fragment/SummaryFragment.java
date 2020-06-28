package com.example.tinycount.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.R;
import com.example.tinycount.db.AccountDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    View mRootView;
    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView=inflater.inflate(R.layout.fragment_summary, container, false);
        initView();
        return mRootView;
        // Inflate the layout for this fragment

    }

    private void initView() {
        //显示余额
        AccountApplication app = (AccountApplication)this.getActivity().getApplication();
        AccountDao dbMangeer = app.getDatabaseManager();

        TextView textViewSummary = (TextView)mRootView.findViewById(R.id.textViewSummary);

        textViewSummary.setText("10000");

        //饼图绘制

    }

}
