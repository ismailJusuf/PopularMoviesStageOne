package com.example.apple.popularmoviesstageone.MovieDetails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.popularmoviesstageone.Base.BaseActivity;
import com.example.apple.popularmoviesstageone.Base.Constants;
import com.example.apple.popularmoviesstageone.Models.Result;
import com.example.apple.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsContarct.View {

    MovieDetailsPresenter presenter;
    Result result;

    @BindView(R.id.movie_poster_iv)
    ImageView moviePosterIv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.release_date_tv)
    TextView releaseDateTv;
    @BindView(R.id.vote_average_tv)
    TextView voteAverageTv;
    @BindView(R.id.plot_synopsis_tv)
    TextView plotSynopsisTv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_movie_details);
        ButterKnife.bind(this);
        presenter = new MovieDetailsPresenter();
        presenter.attachView(this);

        if (getIntent().getSerializableExtra(Constants.MOVIE_OBJECT) != null) {

            result = (Result) getIntent().getSerializableExtra(Constants.MOVIE_OBJECT);

            Picasso.get().load(Constants.IMAGE_BASE_URL + result.getPosterPath()).into(moviePosterIv);
            title_tv.setText(result.getTitle());
            releaseDateTv.setText(getResources().getText(R.string.release_date) + result.getReleaseDate());
            voteAverageTv.setText(getResources().getText(R.string.vote_average) + Double.toString(result.getVoteAverage()));
            plotSynopsisTv.setText(result.getOverview());

        } else {
            onError(getResources().getString(R.string.error));
        }

    }

}
