package com.example.apple.popularmoviesstageone.Base;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    private V view;
    protected Retrofit retrofit;

    @Override
    final public V getView() {
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(4000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(5000, TimeUnit.MILLISECONDS);
        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build();

    }


}
