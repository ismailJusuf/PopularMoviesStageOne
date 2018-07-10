package com.example.apple.popularmoviesstageone.Base;

public interface BaseContract {

    interface View {

        void onError(String msg);

        void showProgress();

        void hideProgress();

    }

    interface Presenter<V extends View> {
        V getView();

        void attachView(V view);
    }

}
