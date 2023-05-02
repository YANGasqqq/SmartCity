package com.example.smartcity1.activity;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1.R;
import com.example.smartcity1.json.LoginJson;

import org.json.JSONException;

import tools.OKHttpTools;
import tools.SPreTools;

public class LoginActivity extends BaseActivity {
    private EditText loginUser;
    private EditText loginPass;
    private TextView loginReg;
    private Button loginBtn;

    @Override
    protected void initData() {
        OKHttpTools.iniOk(this);
           loginBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           String user=loginUser.getText().toString();
                           String pass=loginPass.getText().toString();
                           if (!user.equals("")&&!pass.equals("")){
                               try {
                                   json.put("username",user);
                                   json.put("password",pass);

                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                               String result= OKHttpTools.post("/prod-api/api/login",json);
                              if (!result.equals("500")){
                                  LoginJson loginJson=gson.fromJson(result,LoginJson.class);

                                  if (loginJson.getCode()==200){

                                      Looper.prepare();
                                      SPreTools.setIsGuide(LoginActivity.this,"IsGuide",true);
                                      SPreTools.setUserToken(LoginActivity.this,"Token",loginJson.getToken());
                                      Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                      runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                          }
                                      });
                                      Looper.loop();
                                  }else{
                                      Looper.prepare();
                                      Toast.makeText(LoginActivity.this, loginJson.getMsg(), Toast.LENGTH_SHORT).show();
                                      Looper.loop();
                                  }
                              }else{
                                  Looper.prepare();
                                  Toast.makeText(LoginActivity.this, "服务器错误！", Toast.LENGTH_SHORT).show();
                                  Looper.loop();
                              }

                           }
                       }
                   }).start();

               }
           });
    }

    @Override
    protected void initView() {
        loginUser = (EditText) findViewById(R.id.loginUser);
        loginPass = (EditText) findViewById(R.id.loginPass);
        loginReg = (TextView) findViewById(R.id.loginReg);
        loginBtn = (Button) findViewById(R.id.loginBtn);

    }

    @Override
    protected String getTitleBarTitle() {
        return "登录";
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
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}