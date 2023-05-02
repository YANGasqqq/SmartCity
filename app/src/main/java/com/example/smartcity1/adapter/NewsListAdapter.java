package com.example.smartcity1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity1.R;

public class NewsListAdapter extends BaseAdapter {

    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private List<String> content = new ArrayList<>();
    private List<String> plNum = new ArrayList<>();
    private List<String> date = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public NewsListAdapter(List<String> img, List<String> title, List<String> content, List<String> plNum, List<String> date, Context context) {
        this.img = img;
        this.title = title;
        this.content = content;
        this.plNum = plNum;
        this.date = date;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.news_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews( (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(ViewHolder holder,int i) {
        //TODO implement
        Glide.with(context).load(img.get(i)).into(holder.newsImg);
        holder.newsTitle.setText(title.get(i));
        holder.newsContent.setText(Html.fromHtml(content.get(i)));
        holder.newsPl.setText(plNum.get(i));
        holder.newsDate.setText(date.get(i));
    }

    protected class ViewHolder {
        private ImageView newsImg;
    private TextView newsTitle;
    private TextView newsContent;
    private TextView newsPl;
    private TextView newsDate;

        public ViewHolder(View view) {
            newsImg = (ImageView) view.findViewById(R.id.newsImg);
            newsTitle = (TextView) view.findViewById(R.id.newsTitle);
            newsContent = (TextView) view.findViewById(R.id.newsContent);
            newsPl = (TextView) view.findViewById(R.id.newsPl);
            newsDate = (TextView) view.findViewById(R.id.newsDate);
        }
    }
}
