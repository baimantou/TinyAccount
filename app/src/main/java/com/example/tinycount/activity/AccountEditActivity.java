package com.example.tinycount.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tinycount.R;
import com.example.tinycount.db.AccountDao;
import com.example.tinycount.entity.AccountCategory;
import com.example.tinycount.entity.AccountItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountEditActivity extends AppCompatActivity {
    private List<AccountCategory> categoryList;
    private TextView textViewSelectedType;
    private EditText editTextMoney;
    private EditText editTextRemark;
    private  boolean isIncom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        isIncom=this.getIntent().getBooleanExtra("isIncome",true);
        textViewSelectedType=(TextView)findViewById(R.id.textViewSelectedType);
        editTextMoney=(EditText)findViewById(R.id.editTextMoney);
        editTextRemark=(EditText)findViewById(R.id.editTextRemark);
        if(isIncom)
            textViewSelectedType.setText("工资");
        else
            textViewSelectedType.setText("交通");
        initView();

        final Button buttonOK = (Button) findViewById(R.id.buttonOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOKOnclick();
            }
        });
    }

    private void buttonOKOnclick() {
        AccountItem item = new AccountItem();//输入框获取数据 给item
        item.setCategory(textViewSelectedType.getText().toString());
        item.setRemark(editTextRemark.getText().toString());
        item.setMoney(Double.parseDouble(editTextMoney.getText().toString()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        item.setDate(sdf.format(new Date()));
        AccountDao dbManager = new AccountDao(this);
        if(isIncom){
            dbManager.addIncome(item);
        }else {
            //支出类别
            dbManager.addOutlay(item);
        }
        setResult(1);
        finish();
    }

    private void initView() {
        AccountDao dbManager = new AccountDao(this);
        if(isIncom)
           // getTestDataIncome();
            categoryList = dbManager.getIncomeType();
        else
            categoryList = dbManager.getOutlayType();

        final GridView gridView=(GridView)findViewById(R.id.gridView1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,categoryList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridViewOnItemClick(position);
            }
        });
    }

    private List<AccountCategory>getTestDataOutlay() {
        categoryList=new ArrayList<>();
        categoryList.add(new AccountCategory(1,"交通",R.drawable.traffic_icon));
        categoryList.add(new AccountCategory(2,"食物",R.drawable.breakfast_icon));
        categoryList.add(new AccountCategory(3,"图书",R.drawable.book_icon));
        categoryList.add(new AccountCategory(3,"电影",R.drawable.film_icon));
        return categoryList;
    }

    private List<AccountCategory> getTestDataIncome() {
        categoryList = new ArrayList<>();
        categoryList.add(new AccountCategory(1,"工资",R.drawable.fund_icon));
        categoryList.add(new AccountCategory(2,"奖金",R.drawable.insurance_icon));
        categoryList.add(new AccountCategory(3,"兼职收入",R.drawable.baby_icon));
        return categoryList;
    }

    private void gridViewOnItemClick(int position) {
        textViewSelectedType.setText(categoryList.get(position).toString());
    }
}