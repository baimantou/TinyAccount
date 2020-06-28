package com.example.tinycount.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tinycount.AccountApplication;
import com.example.tinycount.entity.AccountCategory;
import com.example.tinycount.entity.AccountItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    public AccountDao(Context context) {
        helper = new DatabaseHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    //收入类型
    public List<AccountCategory> getIncomeType(){
        ArrayList<AccountCategory> result = new ArrayList<AccountCategory>();
        String sql = "select id,category,icon from AccountIncomeType";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            int icon = cursor.getInt(cursor.getColumnIndex("icon"));
            AccountCategory c = new AccountCategory(id,category,icon);
            result.add(c);
        }
        cursor.close();
        return result;
    }

    //支出类型
    public List<AccountCategory> getOutlayType(){
        ArrayList<AccountCategory> result = new ArrayList<AccountCategory>();
        String sql = "select id,category,icon from AccountOutlayType";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            int icon = cursor.getInt(cursor.getColumnIndex("icon"));
            AccountCategory c = new AccountCategory(id,category,icon);
            result.add(c);
        }
        cursor.close();
        return result;
    }

    //收入类型
    public List<AccountItem> getIncomeList(){
        ArrayList<AccountItem> result = new ArrayList<AccountItem>();
        Cursor cursor = db.query("accountincome", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            AccountItem item = new AccountItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            item.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
            item.setDate(cursor.getString(cursor.getColumnIndex("date")));
            item.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    //支出类型
    public List<AccountItem> getOutlayList(){
        ArrayList<AccountItem> result = new ArrayList<AccountItem>();
        String sql = "select id,category,money,remark,date from AccountOutlay";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            AccountItem item = new AccountItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            item.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
            item.setDate(cursor.getString(cursor.getColumnIndex("date")));
            item.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    //添加收入
    public void addIncome(AccountItem item) {
        db.beginTransaction(); //开启事务
        try {
            db.execSQL("INSERT INTO AccountIncome(id,category,money,date,remark) VALUES(null,?,?,?,?)",
                    new Object[]{item.getCategory(), item.getMoney(),item.getDate(),item.getRemark()});

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加支出
    public void addOutlay(AccountItem item) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO AccountOutlay(id,category,money,date,remark) VALUES(null,?,?,?,?)",
                    new Object[]{item.getCategory(), item.getMoney(),item.getDate(),item.getRemark()});

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    //删除收入
    public void deleteIncome(long id) {
        String sql = "delete from AccountIncome where id="+id;

        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    //添加收入类型
    public void addIncomeCategory(String category,int icon) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO AccountIncomeType(id,category,icon) VALUES(null,?,?)",
                    new Object[]{category,icon});

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加支出3类型
    public void addOutlayCategory(String category,int icon) {
        db.beginTransaction();  //开始事务
        try {
            db.execSQL("INSERT INTO AccountOutlayType(id,category,icon) VALUES(null,?,?)",
                    new Object[]{category,icon});

            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    //支出类型汇总 自己写的 饼图的数据缘
    public List<AccountItem> setOutlayStaticsLite(String date){
        ArrayList<AccountItem> result = new ArrayList<AccountItem>();
        String sql = "select category,sum(money) as money from AccountOutlay where date like'' +date+'%' group by category";

        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            AccountItem item = new AccountItem();
            item.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            item.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
            result.add(item);
        }
        cursor.close();
        return result;
    }
}
