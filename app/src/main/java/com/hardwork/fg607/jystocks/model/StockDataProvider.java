package com.hardwork.fg607.jystocks.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fg607 on 16-9-9.
 */
public class StockDataProvider {

    public static final String BASE_URL = "http://fg.xdty.org:8888/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private IStockDataProvider mIDataProvider;

    //构造方法私有
    private StockDataProvider() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mIDataProvider = mRetrofit.create(IStockDataProvider.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final StockDataProvider INSTANCE = new StockDataProvider();
    }

    //获取单例
    public static StockDataProvider getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getSHCData(Subscriber<List<SHCData>> subscriber){

        mIDataProvider.getSHCData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
