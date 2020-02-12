package com.wangqing.chilemecilent.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.wangqing.chilemecilent.object.ao.User;

/**
 * 账户管理类
 */
public final class AccountManager {
    private static AccountManager INSTANCE;
    private final SharedPreferences sharedPreferences;
    private final ACache aCache;
    private boolean isOnline;
    private String token;
    private User user;

    private AccountManager(Application context){
        sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        aCache = ACache.get(context);
        isOnline = sharedPreferences.getBoolean("isOnline", false);
        token = sharedPreferences.getString("token", null);
        user = (User) aCache.getAsObject("user");
    }

    public static AccountManager getInstance(Application application){
        if (INSTANCE == null){
            synchronized (AccountManager.class){
                if (INSTANCE == null){
                    INSTANCE = new AccountManager(application);
                }
            }
        }
        return INSTANCE;
    }

    public void setOnline(boolean isOnline){
        this.isOnline = isOnline;
        sharedPreferences.edit().putBoolean("isOnline", isOnline).apply();
    }

    public void setToken(String token){
        this.token = token;
        sharedPreferences.edit().putString("token", token).apply();
    }

    public boolean isOnline(){
        return isOnline && !TextUtils.isEmpty(token);
    }

    public String getToken(){
        return token;
    }

    public void setUser(User user){
        aCache.put("user", user);
    }

    public User getUser(){
        return user;
    }

    public void logOut(){
        setOnline(false);
        setToken(null);
        setUser(null);
    }


}
