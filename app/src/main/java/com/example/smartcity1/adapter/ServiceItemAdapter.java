package com.example.smartcity1.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.example.smartcity1.R;

public class ServiceItemAdapter extends BaseAdapter {

    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ServiceItemAdapter(List<String> img, List<String> title, Context context) {
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
            convertView = layoutInflater.inflate(R.layout.service_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews( ViewHolder holder,int i) {
        //TODO implement
        holder.serviceTitle.setText(title.get(i));
        Glide.with(context).load(img.get(i)).into(holder.serviceImg);
    }

    protected class ViewHolder {
        private ImageView serviceImg;
    private TextView serviceTitle;

        public ViewHolder(View view) {
            serviceImg = (ImageView) view.findViewById(R.id.serviceImg);
            serviceTitle = (TextView) view.findViewById(R.id.serviceTitle);
        }
    }
}
