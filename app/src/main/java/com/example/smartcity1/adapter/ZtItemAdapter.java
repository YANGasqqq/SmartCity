package com.example.smartcity1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity1.R;

public class ZtItemAdapter extends BaseAdapter {

    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ZtItemAdapter(List<String> img, List<String> title, Context context) {
        this.img = img;
        this.title = title;
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
            convertView = layoutInflater.inflate(R.layout.zt_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(ViewHolder holder, int i) {
        //TODO implement
        holder.ztTitle.setText(title.get(i));
        Log.i("TAG", "initializeViews: "+img.get(i));
        Glide.with(context).load(img.get(i)).into(holder.ztImg);
    }
    protected class ViewHolder {
        private ImageView ztImg;
    private TextView ztTitle;

        public ViewHolder(View view) {
            ztImg = (ImageView) view.findViewById(R.id.ztImg);
            ztTitle = (TextView) view.findViewById(R.id.ztTitle);
        }
    }
}
