package com.example.apple.popularmoviesstageone.Main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.apple.popularmoviesstageone.Adapters.MoviesAdapter;
import com.example.apple.popularmoviesstageone.Base.BaseActivity;
import com.example.apple.popularmoviesstageone.Models.Result;
import com.example.apple.popularmoviesstageone.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    MainPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter();
        presenter.attachView(this);

        presenter.getPopularMovies();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.top_rated_movies_item:
                presenter.getTopRatedMovies();
                break;
            case R.id.popular_movies_item:
                presenter.getPopularMovies();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMoviesAdapter(List<Result> results) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MoviesAdapter(this, results));
    }
}
