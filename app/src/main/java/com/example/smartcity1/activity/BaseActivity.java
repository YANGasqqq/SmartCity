package com.example.smartcity1.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity1.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import tools.SPreTools;

public abstract class BaseActivity extends AppCompatActivity {
    private View titleBarView;
    private RelativeLayout titleBarBg;
    private ImageView titleBarLeft;
    private TextView titleBarTitle;
    public  static String IPPort;
    public Gson gson=new Gson();
    public JSONObject json=new JSONObject();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
       setContentView(getLayoutId());
        IPPort= "http://"+ SPreTools.getIPPort(this,"IPPort");
        initTitleBar();
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    private void initTitleBar() {
        titleBarView = (View) findViewById(R.id.titleBarView);
        titleBarBg = (RelativeLayout) findViewById(R.id.titleBarBg);
        titleBarLeft = (ImageView) findViewById(R.id.titleBarLeft);
        titleBarTitle = (TextView) findViewById(R.id.titleBarTitle);
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
                    finish();
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

    protected abstract int getLayoutId();
}
