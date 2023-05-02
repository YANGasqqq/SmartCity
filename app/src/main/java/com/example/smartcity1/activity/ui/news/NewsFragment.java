package com.example.smartcity1.activity.ui.news;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.smartcity1.R;
import com.example.smartcity1.activity.BaseActivity;
import com.example.smartcity1.activity.ui.BaseFragment;

public class NewsFragment extends BaseFragment {

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
        return "新闻";
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
        return R.layout.fragment_news;
    }
}
