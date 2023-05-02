package com.example.smartcity1.activity.ui.cpc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartcity1.R;

import com.example.smartcity1.activity.ui.BaseFragment;

public class CpcFragment extends BaseFragment {

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
        return "智慧党建";
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
        return R.layout.fragment_cpc;
    }
}