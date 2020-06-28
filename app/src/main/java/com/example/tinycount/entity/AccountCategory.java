package com.example.tinycount.entity;

public class AccountCategory {
    private int id;
    private String category; //类别
    private int icon; //图标

    //定义带参构造方法
    public AccountCategory(int id, String category, int icon) {
        this.id = id;
        this.category = category;
        this.icon = icon;
    }

    //定义不带参数ID的方法
    public AccountCategory(String category, int icon) {
        this.category = category;
        this.icon = icon;
    }

    //不带参数的构造方法
    public AccountCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return category;
    }
}
