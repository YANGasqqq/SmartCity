package com.example.smartcity1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartcity1.R;

public class RegActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected String getTitleBarTitle() {
        return "注册";
    }

    @Override
    protected boolean isTitleLeft() {
        return false;
    }

    @Override
    protected int getTitleBarBg() {
        return R.color.colorBg;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reg;
    }
}