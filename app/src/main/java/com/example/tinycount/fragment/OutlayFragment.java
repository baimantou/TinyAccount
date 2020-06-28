package com.example.tinycount.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.R;
import com.example.tinycount.activity.AccountEditActivity;
import com.example.tinycount.adapter.OutlayRecyclerViewAdapter;
import com.example.tinycount.db.AccountDao;
import com.example.tinycount.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutlayFragment extends Fragment {

    private View mRootView;
    private RecyclerView mRecyclerView;
    public OutlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_outlay, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        refresh();
        final Button buttonAdd=(Button)mRootView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddOnClick();
            }
        });

    }
    protected void buttonAddOnClick() {
        Intent intent=new Intent(this.getActivity(), AccountEditActivity.class);
        intent.putExtra("isIncome",false);
        startActivityForResult(intent, 1);
       // startActivity(intent);
    }
    private void refresh() {
        AccountApplication app = (AccountApplication)this.getActivity().getApplication();
        AccountDao dbMangeer = app.getDatabaseManager();
        List<AccountItem> outlayAccountList= dbMangeer.getOutlayList();

        mRecyclerView=(RecyclerView)mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(new OutlayRecyclerViewAdapter(this.getActivity(),outlayAccountList));
        TextView textViewOutlaySummary=mRootView.findViewById(R.id.textViewOutlaySummary);
        textViewOutlaySummary.setText("2000");
    }

    private List<AccountItem> getTestData() {
        List<AccountItem> result = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            AccountItem item = new AccountItem();
            item.setId(i);
            item.setCategory("食物");
            item.setMoney(100 * i);
            item.setDate("2019-01-0" + i);
            result.add(item);
        }
        return result;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        refresh();
    }
}
