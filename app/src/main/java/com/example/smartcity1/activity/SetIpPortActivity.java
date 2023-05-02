package com.example.smartcity1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity1.R;

import tools.SPreTools;

public class SetIpPortActivity extends BaseActivity {
    private EditText editIP;
    private Button saveBtn;

    @Override
    protected void initData() {
       if (!SPreTools.getIPPort(this,"IPPort").equals("")){
           editIP.setText(SPreTools.getIPPort(this,"IPPort"));
       }
       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String ip=editIP.getText().toString();

               if (!ip.equals("")){
                   SPreTools.setIPPort(SetIpPortActivity.this,"IPPort",ip);
                   Toast.makeText(SetIpPortActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                   finish();
               }else{
                   Toast.makeText(SetIpPortActivity.this, "IP不能为空", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    @Override
    protected void initView() {


        editIP = (EditText) findViewById(R.id.editIP);
        saveBtn = (Button) findViewById(R.id.saveBtn);

    }

    @Override
    protected String getTitleBarTitle() {
        return "IP端口设置";
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
        return R.layout.activity_set_ip_port;
    }
}