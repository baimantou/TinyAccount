package com.example.tinycount.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tinycount.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "account.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        //建库
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
        //收入类别
        String sql = "CREATE TABLE accountincometype(id integer primary key autoincrement,category text,icon integer)";
        db.execSQL(sql);
        //收入明细表(id,类别，金额，备注，日期时间)
        sql = "CREATE TABLE accountincome(id integer primary key autoincrement,category text,"+
                "money double,remark text,date text)";
        db.execSQL(sql);

        //支出类别
        sql = "CREATE TABLE accountoutlaytype(id integer primary key autoincrement,category text,icon integer)";
        db.execSQL(sql);
        //支出明细表(id,类别，金额，备注，日期时间)
        sql = "CREATE TABLE accountoutlay(id integer primary key autoincrement,category text,"+
                "money double,remark text,date text)";
        db.execSQL(sql);

        //初始化的数据
        initData(db);
    }
    //自动增长的列表，不需要给值；某个字段不想给值，不出现在表名后的列表中
    private void initData(SQLiteDatabase db) {
        //收入类别
        String sql = String.format("insert into accountincometype(category,icon) values('工资',%d)", R.drawable.fund_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountincometype(category,icon) values('奖金',%d)", R.drawable.insurance_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountincometype(category,icon) values('兼职收入',%d)", R.drawable.baby_icon);
        db.execSQL(sql);

        //支出类别
        sql = String.format("insert into accountoutlaytype(category,icon) values('交通',%d)", R.drawable.traffic_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountoutlaytype(category,icon) values('食物',%d)", R.drawable.breakfast_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountoutlaytype(category,icon) values('图书',%d)", R.drawable.book_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountoutlaytype(category,icon) values('电影',%d)", R.drawable.film_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountoutlaytype(category,icon) values('房租',%d)", R.drawable.housing_loan_icon);
        db.execSQL(sql);
        sql = String.format("insert into accountoutlaytype(category,icon) values('运动',%d)", R.drawable.sport_icon);
        db.execSQL(sql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        //收入明细
        sql = "insert into accountincome(category,money,date) values('工资',10000,'"+currentDate+"')";
        db.execSQL(sql);
        sql = "insert into accountincome(category,money,date) values('奖金',1000,'"+currentDate+"')";
        db.execSQL(sql);

        //支出明细
        sql = "insert into accountoutlay(category,money,date) values('交通',100,'"+currentDate+"')";
        db.execSQL(sql);
        sql = "insert into accountoutlay(category,money,date) values('食物',200,'"+currentDate+"')";
        db.execSQL(sql);
        sql = "insert into accountoutlay(category,money,date) values('图书',150,'"+currentDate+"')";
        db.execSQL(sql);
        sql = "insert into accountoutlay(category,money,date) values('电影',100,'"+currentDate+"')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
