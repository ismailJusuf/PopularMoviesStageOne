package com.example.apple.popularmoviesstageone.Base;

import com.example.apple.popularmoviesstageone.Models.Response.DiscoverResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestServices {

    @GET("movie/popular")
    Observable<DiscoverResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<DiscoverResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
