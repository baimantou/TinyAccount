package com.example.tinycount.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.R;
import com.example.tinycount.activity.AccountEditActivity;
import com.example.tinycount.adapter.AccountItemAdapter;
import com.example.tinycount.db.AccountDao;
import com.example.tinycount.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {

    View mRootView;
    ListView listView;
    public IncomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_income, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        listView=(ListView) mRootView.findViewById(R.id.listView1);
        refresh();
        final Button buttonAdd=(Button)mRootView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddOnClick();
            }
        });
        ListView listView = (ListView)mRootView.findViewById(R.id.listView1);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteItem(l);
                return true;
            }
        });

    }

    private void deleteItem(final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(R.string.delete_confirm_title);
        builder.setMessage(R.string.delete_confirm_msg);

        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AccountDao dbManager = new AccountDao(getContext());
                dbManager.deleteIncome(id);
                refresh();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    protected void buttonAddOnClick() {
        Intent intent=new Intent(this.getActivity(), AccountEditActivity.class);
        intent.putExtra("isIncome",true);
     //   startActivity(intent);

        startActivityForResult(intent,1);

    }

    private void refresh() {

        //从数据库中读取数据显示
      //  AccountDao dbMangeer = new AccountDao(getActivity());
        AccountApplication app = (AccountApplication)this.getActivity().getApplication();
        AccountDao dbMangeer = app.getDatabaseManager();
        List<AccountItem> incomeAccountList=dbMangeer.getIncomeList();

        AccountItemAdapter adapter=new AccountItemAdapter(incomeAccountList,getActivity());
        listView.setAdapter(adapter);
        TextView textViewIncomeSummary=(TextView)mRootView.findViewById(R.id.textViewIncomeSummary);
        textViewIncomeSummary.setText("10000");
    }

    private List<AccountItem> getTestData(){
        List<AccountItem> result=new ArrayList<AccountItem>();
        for(int i=0;i<5;i++){
            AccountItem item=new AccountItem();
            item.setId(i);
            item.setCategory("兼职收入");
            item.setMoney(i*100);
            item.setDate("2019-01-0"+i);
            result.add(item);
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         refresh();
    }
}
