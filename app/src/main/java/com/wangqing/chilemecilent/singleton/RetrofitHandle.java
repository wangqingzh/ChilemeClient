package com.wangqing.chilemecilent.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 生成处理webapi的Retrofit单例
 * 并在本单例中进行一些相关配置
 */
public class RetrofitHandle {
    public static final String BASE_URL = "https://192.168.2.225:8443/api/v1/";
    public static final int CONNECT_TIME_OUT = 10000;
    private Retrofit mRetrofit;

    /**
     * 构造方法
     */
    private RetrofitHandle() {
        createRetrofit();
    }

    private void createRetrofit() {
        // ******************https证书问题的暂时解决 信任所有证书****************************************
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        // *****************************************************************************************

            // 日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)

                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static RetrofitHandle sRetrofitCreator = null;

    /**
     * @return 单例
     */
    public static RetrofitHandle getInstance() {
        if (sRetrofitCreator == null) {
            synchronized (RetrofitHandle.class) {
                if (sRetrofitCreator == null) {
                    sRetrofitCreator = new RetrofitHandle();
                }
            }
        }
        return sRetrofitCreator;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
