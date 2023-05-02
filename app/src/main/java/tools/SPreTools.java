package tools;

import android.content.Context;
import android.content.SharedPreferences;

public class SPreTools {
    public  static void setIPPort(Context context,String key,String value){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();;
        editor.putString(key,value);
        editor.commit();
    }
    public  static String getIPPort(Context context,String key){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  preferences.getString(key,"");
    }
    public  static void setUserToken(Context context,String key,String value){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();;
        editor.putString(key,value);
        editor.commit();
    }
    public  static String getUserToken(Context context,String key){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  preferences.getString(key,"");
    }
    public  static void setIsGuide(Context context,String key,Boolean value){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();;
        editor.putBoolean(key,value);
        editor.commit();
    }
    public  static Boolean getIsGuide(Context context,String key){
        SharedPreferences preferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  preferences.getBoolean(key,false);
    }
}
