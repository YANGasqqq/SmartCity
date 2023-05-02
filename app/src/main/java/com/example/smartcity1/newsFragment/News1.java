package com.example.smartcity1.newsFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.smartcity1.R;
import com.example.smartcity1.activity.NewsDetailsActivity;
import com.example.smartcity1.adapter.NewsListAdapter;
import com.example.smartcity1.costomView.MyListView;
import com.example.smartcity1.json.NewsListJson;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tools.OKHttpTools;
import tools.SPreTools;

public class News1 extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private static View root;
    private MyListView list;
    public  static String IPPort;
    public Gson gson=new Gson();
    public JSONObject json=new JSONObject();
    private static FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (root==null){
            root = inflater.inflate(R.layout.fragment_news1,container,false);
            IPPort= "http://"+ SPreTools.getIPPort(root.getContext(),"IPPort");
            mActivity=getActivity();
            initView();
            initData();
        }
        return root;
    }

    private void initView() {
        list = root.findViewById(R.id.list);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= OKHttpTools.get("/prod-api/press/press/list?type=9"); //9,17,19,20,21,22
                final List<String> img=new ArrayList<>();
                final List<String> title=new ArrayList<>();
                final List<String> content=new ArrayList<>();
                final List<String> plNum=new ArrayList<>();
                final List<String> date=new ArrayList<>();
                final List<Integer> newsId=new ArrayList<>();
                NewsListJson newsListJson=gson.fromJson(result,NewsListJson.class);
                for (int i = 0; i < newsListJson.getTotal(); i++) {
                    img.add(IPPort+newsListJson.getRows().get(i).getCover());
                    title.add(newsListJson.getRows().get(i).getTitle());
                    content.add(newsListJson.getRows().get(i).getContent());
                    plNum.add(String.valueOf(newsListJson.getRows().get(i).getCommentNum()));
                    date.add(newsListJson.getRows().get(i).getPublishDate());
                    newsId.add(newsListJson.getRows().get(i).getId());
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(new NewsListAdapter(img,title,content,plNum,date,mActivity.getApplicationContext()));
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(root.getContext(), NewsDetailsActivity.class);
                                intent.putExtra("newsId",newsId.get(i));
                                mActivity.startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
