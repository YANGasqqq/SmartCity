package com.example.smartcity1.activity.ui.user;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.smartcity1.R;
import com.example.smartcity1.activity.ui.BaseFragment;

public class UserFragment extends BaseFragment {
    @SuppressLint("StaticFieldLeak")
    private static View root;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        root = view;
    }

    @Override
    protected String getTitleBarTitle() {
        return "个人中心";
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
    protected View getRootView() {
        return root;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }
}
