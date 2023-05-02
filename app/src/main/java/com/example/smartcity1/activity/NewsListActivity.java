package com.example.smartcity1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smartcity1.R;
import com.example.smartcity1.adapter.NewsListAdapter;
import com.example.smartcity1.costomView.MyListView;

import java.util.ArrayList;
import java.util.Objects;

public class NewsListActivity extends BaseActivity {
    private ListView list;

    @Override
    protected void initData() {
         Intent intent=getIntent();
        final ArrayList<Integer> newsId=intent.getIntegerArrayListExtra("newsId");
         list.setAdapter(new NewsListAdapter(
                 intent.getStringArrayListExtra("img"),
                 intent.getStringArrayListExtra("title"),
                 intent.getStringArrayListExtra("content"),
                 intent.getStringArrayListExtra("plNum"),
                 intent.getStringArrayListExtra("date"),
                 this

                 ));
         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent=new Intent(NewsListActivity
                 .this, NewsDetailsActivity.class);
                 assert newsId != null;
                 intent.putExtra("newsId",newsId.get(i) );
                startActivity(intent);
             }
         });
    }

    @Override
    protected void initView() {
        list =  findViewById(R.id.list);

    }

    @Override
    protected String getTitleBarTitle() {
        return "新闻列表";
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
        return R.layout.activity_news_list;
    }
}