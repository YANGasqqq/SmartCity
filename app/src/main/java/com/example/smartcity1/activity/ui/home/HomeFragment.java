package com.example.smartcity1.activity.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartcity1.R;

import com.example.smartcity1.activity.NewsDetailsActivity;
import com.example.smartcity1.activity.NewsListActivity;
import com.example.smartcity1.adapter.ServiceItemAdapter;
import com.example.smartcity1.adapter.TabAdapter;
import com.example.smartcity1.adapter.ZtItemAdapter;
import com.example.smartcity1.costomView.MyGridView;
import com.example.smartcity1.costomView.MyViewPager;
import com.example.smartcity1.json.BannerJson;
import com.example.smartcity1.activity.ui.BaseFragment;
import com.example.smartcity1.json.NewsListJson;
import com.example.smartcity1.json.ServiceJson;
import com.example.smartcity1.newsFragment.News1;
import com.example.smartcity1.newsFragment.News2;
import com.example.smartcity1.newsFragment.News3;
import com.example.smartcity1.newsFragment.News4;
import com.example.smartcity1.newsFragment.News5;
import com.example.smartcity1.newsFragment.News6;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import tools.MyImgLoader;
import tools.OKHttpTools;

public class HomeFragment extends BaseFragment {
    private Banner homeBanner;
    private GridView homeService;
    private MyGridView homeZt;
    private TabLayout homeTab;
    private MyViewPager homeVp;
    SwipeRefreshLayout swIp;
     private EditText editSearch;

    @SuppressLint("StaticFieldLeak")
    private static View root;
    private NewsListJson newsListJson;

    @Override
    protected    void initData() {
        OKHttpTools.iniOk(mActivity.getApplicationContext());
        thread.start();

        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String value = editSearch.getText().toString();
                ArrayList<String> img = new ArrayList<>();
                ArrayList<String> title = new ArrayList<>();
                ArrayList<String> content = new ArrayList<>();
                ArrayList<String> plNum = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();
                ArrayList<Integer> newsId = new ArrayList<>();
                if (!value.equals("")) {
//                    Log.i("TAG", "onEditorAction:数量 "+ newsListJson.getTotal());

                    for (int j = 0; j < newsListJson.getTotal(); j++) {
                        String titles = newsListJson.getRows().get(j).getTitle();
                        if (titles.contains(value)) {
//                            Log.i("TAG", "onEditorAction: "+IPPort+newsListJson.getRows().get(i).getCover());
                            img.add(IPPort + newsListJson.getRows().get(j).getCover());
                            title.add(newsListJson.getRows().get(j).getTitle());
                            content.add(newsListJson.getRows().get(j).getContent());
                            plNum.add(String.valueOf(newsListJson.getRows().get(j).getCommentNum()));
                            date.add(newsListJson.getRows().get(j).getPublishDate());
                            newsId.add(newsListJson.getRows().get(j).getId());
                        } else {
                            Log.i("TAG", "onEditorAction:没有 ");

                        }

                    }
                    if (!img.isEmpty()) {
                        Intent intent = new Intent(root.getContext(), NewsListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("img", img);
                        bundle.putStringArrayList("title", title);
                        bundle.putStringArrayList("content", content);
                        bundle.putStringArrayList("plNum", plNum);
                        bundle.putStringArrayList("date", date);
                        bundle.putIntegerArrayList("newsId", newsId);
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                        return true;
                    } else {
                        Toast.makeText(root.getContext(), "搜索结果为空！", Toast.LENGTH_SHORT).show();
                    }
                    return true;

                } else {
                    return false;

                }


            }

        });

    }

    Thread thread=new Thread(new Runnable() {
      @Override
      public synchronized void run() {
          initBanner();
          initService();
          initZt();
          initTab();
      }
  });
    private   void initTab() {

        final List<Fragment> list=new ArrayList<>();
        list.add(new News1());
        list.add(new News2());
        list.add(new News3());
        list.add(new News4());
        list.add(new News5());
        list.add(new News6());
      mActivity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
              homeVp.setAdapter(new TabAdapter(getChildFragmentManager(),list));
              homeTab.setupWithViewPager(homeVp);
              homeVp.setOffscreenPageLimit(5);

          }
      });

    }



    private void initZt() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= OKHttpTools.get("/prod-api/press/press/list");
                newsListJson = gson.fromJson(result,NewsListJson.class);
                final List<String> img=new ArrayList<>();
                final List<String> title=new ArrayList<>();
                final List<Integer> newsId=new ArrayList<>();
                for (int i = 0; i <4; i++) {
                    img.add(IPPort+ newsListJson.getRows().get(i).getCover());
                    title.add(newsListJson.getRows().get(i).getTitle());
                    newsId.add(newsListJson.getRows().get(i).getId());
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeZt.setAdapter(new ZtItemAdapter(img,title,mActivity.getApplicationContext()));
                       homeZt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void initService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= OKHttpTools.get("/prod-api/api/service/list");
                ServiceJson serviceJson=gson.fromJson(result,ServiceJson.class);
                final List<String> img=new ArrayList<>();
                final List<String> title=new ArrayList<>();
                for (int i = 0; i <5; i++) {
                    img.add(IPPort+serviceJson.getRows().get(i).getImgUrl());
                    title.add(serviceJson.getRows().get(i).getServiceName());
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       homeService.setAdapter(new ServiceItemAdapter(img,title,mActivity.getApplicationContext()));

                    }
                });
            }
        }).start();
    }

    private void initBanner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= OKHttpTools.get("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2");
                BannerJson bannerJson=gson.fromJson(result,BannerJson.class);
                final List<String> img=new ArrayList<>();
                final List<Integer> newsId=new ArrayList<>();
                for (int i = 0; i < bannerJson.getTotal(); i++) {
                   img.add(IPPort+bannerJson.getRows().get(i).getAdvImg());
                   newsId.add(bannerJson.getRows().get(i).getTargetId());
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeBanner.setImages(img);
                        homeBanner.setImageLoader(new MyImgLoader());
                        homeBanner.start();
                        homeBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int i) {
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

    @Override
    protected void initView(View view) {
        root = view;
        homeBanner = (Banner) root.findViewById(R.id.homeBanner);
        homeService = (GridView) root.findViewById(R.id.homeService);
        homeZt = (MyGridView) root.findViewById(R.id.homeZt);
        homeTab = (TabLayout) root.findViewById(R.id.homeTab);
        homeVp = (MyViewPager) root.findViewById(R.id.homeVp);
        editSearch=root.findViewById(R.id.editSearch);
    }

    @Override
    protected String getTitleBarTitle() {
        return "智慧城市";
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
        return R.layout.fragment_home;
    }
}