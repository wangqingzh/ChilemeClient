package com.wangqing.chilemecilent.singleton;
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
    public static final String BASE_URL = "https://pixabay.com/api/";
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
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static RetrofitHandle sRetrofitCteator = null;

    /**
     *
     * @return 单例
     */
    public static RetrofitHandle getInstance(){
        if (sRetrofitCteator == null){
            synchronized (RetrofitHandle.class){
                if (sRetrofitCteator == null){
                    sRetrofitCteator = new RetrofitHandle();
                }
            }
        }
        return sRetrofitCteator;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
