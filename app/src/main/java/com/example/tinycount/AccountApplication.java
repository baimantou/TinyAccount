package com.example.tinycount;

import android.app.Application;

import com.example.tinycount.db.AccountDao;

public class AccountApplication extends Application {
    private AccountDao mDatabaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseManager = new AccountDao(this);
    }

    public AccountDao getDatabaseManager(){
        return mDatabaseManager;
    }


}
