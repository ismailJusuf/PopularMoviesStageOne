package com.example.apple.popularmoviesstageone.Main;

import com.example.apple.popularmoviesstageone.Base.BaseContract;
import com.example.apple.popularmoviesstageone.Models.Result;

import java.util.List;

public interface MainContract extends BaseContract {

    interface View extends BaseContract.View {

        void setMoviesAdapter(List<Result> results);

    }

    interface Presenter<V extends View> {

        void getPopularMovies();

        void getTopRatedMovies();

    }

}
