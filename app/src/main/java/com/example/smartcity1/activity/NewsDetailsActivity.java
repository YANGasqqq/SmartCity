package com.example.smartcity1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity1.R;
import com.example.smartcity1.json.NewsDetailsJson;

import tools.OKHttpTools;

public class NewsDetailsActivity extends BaseActivity {
    private TextView detailsTitle;
    private ImageView detailsIv;
    private TextView detailsTv;
    private TextView detailsPl;
    private TextView   detailsQw;
    private boolean isShou=true;
    SwipeRefreshLayout swip;

    @Override
    protected void initData() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               int newsId=getIntent().getIntExtra("newsId",0);
               String result= OKHttpTools.get("/prod-api/press/press/"+newsId);
               final NewsDetailsJson newsDetailsJson=gson.fromJson(result,NewsDetailsJson.class);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       detailsTitle.setText(newsDetailsJson.getData().getTitle());
                       Glide.with(NewsDetailsActivity.this).load(IPPort+newsDetailsJson.getData().getCover()).into(detailsIv);
                       detailsTv.setText(Html.fromHtml(newsDetailsJson.getData().getContent()));
                       detailsPl.setText( String.valueOf( newsDetailsJson.getData().getCommentNum()));

                       if (detailsTv.getMaxLines()>3){

                           detailsTv.setMaxLines(3);
                           detailsTv.setEllipsize(TextUtils.TruncateAt.END);
                           detailsQw.setText("查看全文");
                           detailsQw.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {

                                   if (isShou){
                                       detailsTv.setMaxLines(Integer.MAX_VALUE);
                                       isShou=false;
                                       detailsQw.setTextColor(getResources().getColor(R.color.colorRed));
                                       detailsQw.setText("收起文章");
                                   }else{
                                       detailsTv.setMaxLines(3);
                                       isShou=true;
                                       detailsQw.setTextColor(getResources().getColor(R.color.colorBg));
                                       detailsTv.setEllipsize(TextUtils.TruncateAt.END);
                                       detailsQw.setText("查看全文");
                                   }

                               }
                           });

                       }
                   }
               });
           }
       }).start();
    }

    @Override
    protected void initView() {
        detailsTitle = (TextView) findViewById(R.id.detailsTitle);
        detailsIv = (ImageView) findViewById(R.id.detailsIv);
        detailsTv = (TextView) findViewById(R.id.detailsTv);
        detailsPl = (TextView) findViewById(R.id.detailsPl);
        detailsQw = findViewById(R.id.detailsQw);
    }

    @Override
    protected String getTitleBarTitle() {
        return "新闻详情";
    }

    @Override
    protected boolean isTitleLeft() {
        return true;
    }

    @Override
    protected int getTitleBarBg() {
        return R.color.colorBg;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_details;
    }
}