package com.example.smartcity1.activity.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.smartcity1.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import tools.SPreTools;

public abstract class BaseFragment extends Fragment {

    private  View root;
    private View titleBarView;
    private RelativeLayout titleBarBg;
    private ImageView titleBarLeft;
    private TextView titleBarTitle;
    public static FragmentActivity mActivity;
    public  static String IPPort;
    public Gson gson=new Gson();
    public JSONObject json=new JSONObject();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=getRootView();
        if (root==null)
        {
            root = inflater.inflate(getLayoutId(),container,false);
            IPPort= "http://"+ SPreTools.getIPPort(root.getContext(),"IPPort");

            initTitleBar();
            mActivity=getActivity();
            initView(root);
            initData();
        }

        return getRootView();
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    private void initTitleBar() {
        titleBarView = (View) root.findViewById(R.id.titleBarView);
        titleBarBg = (RelativeLayout) root.findViewById(R.id.titleBarBg);
        titleBarLeft = (ImageView) root.findViewById(R.id.titleBarLeft);
        titleBarTitle = (TextView) root.findViewById(R.id.titleBarTitle);
        ViewGroup.LayoutParams params = titleBarView.getLayoutParams();
//        params.height = getStatusHeight();
//        titleBarView.setLayoutParams(params);
        titleBarView.setBackgroundResource(getTitleBarBg());
        titleBarBg.setBackgroundResource(getTitleBarBg());
        if (isTitleLeft()){
            titleBarLeft.setVisibility(View.VISIBLE);
            titleBarLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.finish();
                }
            });
        }else{
            titleBarLeft.setVisibility(View.GONE);
        }
        titleBarTitle.setText(getTitleBarTitle());
    }
    protected abstract String getTitleBarTitle();

    protected abstract boolean isTitleLeft();

    protected abstract int getTitleBarBg();

    private int getStatusHeight() {
        int resId=getResources().getIdentifier("status_bar_height","dimen","android");

        return getResources().getDimensionPixelOffset(resId);
    }
    protected abstract View getRootView();

    protected abstract int getLayoutId();
}
