package com.example.apple.popularmoviesstageone.Main;

import android.util.Log;

import com.example.apple.popularmoviesstageone.Base.BasePresenter;
import com.example.apple.popularmoviesstageone.Base.Constants;
import com.example.apple.popularmoviesstageone.Base.RestServices;
import com.example.apple.popularmoviesstageone.Models.Response.DiscoverResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void getPopularMovies() {

        retrofit.create(RestServices.class)
                .getPopularMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<DiscoverResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showProgress();
                    }

                    @Override
                    public void onNext(DiscoverResponse discoverResponse) {

                        if (discoverResponse.getPage() >= 0) {
                            getView().setMoviesAdapter(discoverResponse.getResultList());
                        } else {
                            getView().onError("Oops, Error");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideProgress();
                        Log.d("BasePresenter", "PopularMovies, bad http req", e);
                        getView().onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideProgress();
                    }

                });
    }

    @Override
    public void getTopRatedMovies() {

        retrofit.create(RestServices.class)
                .getTopRatedMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<DiscoverResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showProgress();
                    }

                    @Override
                    public void onNext(DiscoverResponse discoverResponse) {

                        if (discoverResponse.getPage() >= 0) {
                            getView().setMoviesAdapter(discoverResponse.getResultList());
                        } else {
                            getView().onError("Oops, Error");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideProgress();
                        Log.d("BasePresenter", "TopRatedMovies, bad http req", e);
                        getView().onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideProgress();
                    }

                });

    }
}
