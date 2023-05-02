package com.example.smartcity1.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.smartcity1.R;
import com.youth.banner.Banner;

import java.util.Arrays;

import tools.MyImgLoader;
import tools.SPreTools;

public class GuideActivity extends AppCompatActivity {
       private Integer [] resId={R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4,R.drawable.guide5};
    private Banner guideBanner;
    private TextView guideIP;
    private TextView guideBtn;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//         getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_guide);
//        Log.i("IsGuide", "onStart: "+SPreTools.getIsGuide(this,"IsGuide"));

        guideBanner = (Banner) findViewById(R.id.guideBanner);
        guideIP = (TextView) findViewById(R.id.guideIP);
        guideBtn = (TextView) findViewById(R.id.guideBtn);
        guideBanner.setImageLoader(new MyImgLoader());
        guideBanner.setImages(Arrays.asList(resId));
        guideBanner.isAutoPlay(false);
        guideBanner.start();
        guideBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==resId.length-1){
                    guideIP.setVisibility(View.VISIBLE);
                    guideBtn.setVisibility(View.VISIBLE);
                    guideIP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                              startActivity(new Intent(GuideActivity.this,SetIpPortActivity.class));
                        }
                    });
                    guideBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                            finish();

                        }
                    });
                }else{
                    guideIP.setVisibility(View.GONE);
                    guideBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {


        super.onStart();
//        if (SPreTools.getIsGuide(GuideActivity.this,"IsGuide")){
//            startActivity(new Intent(GuideActivity.this, MainActivity.class));
//            finish();
//        }
//        Log.i("TAG", "onStart: "+SPreTools.getUserToken(this,"Token"));
    }
}