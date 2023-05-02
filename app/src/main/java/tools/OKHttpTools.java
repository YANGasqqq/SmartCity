package tools;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpTools  {

    private static String ipPort;
    private static String token;
    static MediaType type=MediaType.parse("application/json");
    public static void iniOk(Context context){
        ipPort = "http://"+SPreTools.getIPPort(context, "IPPort");
        token = SPreTools.getUserToken(context, "Token");
       if (token.length()>0&&SPreTools.getIsGuide(context,"IsGuide")){
           token="";
       }
    }
    public static String get(String url){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .addHeader("Authorization",token)
                .url(ipPort+url)
                .get()
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful() ) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }
    public static String post(String url, JSONObject json){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(type,String.valueOf(json));
        Request request=new Request.Builder()
                .addHeader("Authorization",token)
                .url(ipPort+url)
                .post(requestBody)
                .build();
        Log.i("Tag", "post: "+ipPort+url);
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful() ) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }
    public static String put(String url, JSONObject json){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(type,String.valueOf(json));
        Request request=new Request.Builder()
                .addHeader("Authorization",token)
                .url(ipPort+url)
                .put(requestBody)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response.isSuccessful() ) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }
}
