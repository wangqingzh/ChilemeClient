package com.wangqing.chilemecilent.singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wangqing.chilemecilent.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 生成处理webapi的Retrofit单例
 * 并在本单例中进行一些相关配置
 */
public class RetrofitHandle {
    public static final String BASE_URL = "http://192.168.2.225:8080/api/v1/";
    public static final int CONNECT_TIME_OUT = 10000;
    private Retrofit mRetrofit;

    /**
     * 构造方法
     */
    private RetrofitHandle(){
        createRetrofit();
    }

    private void createRetrofit() {
        // 日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .serializeNulls()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    private static RetrofitHandle sRetrofitCreator = null;

    /**
     *
     * @return 单例
     */
    public static RetrofitHandle getInstance(){
        if (sRetrofitCreator == null){
            synchronized (RetrofitHandle.class){
                if (sRetrofitCreator == null){
                    sRetrofitCreator = new RetrofitHandle();
                }
            }
        }
        return sRetrofitCreator;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
