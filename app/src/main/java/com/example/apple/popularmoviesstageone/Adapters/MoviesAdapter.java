package com.example.apple.popularmoviesstageone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.popularmoviesstageone.Base.Constants;
import com.example.apple.popularmoviesstageone.Models.Result;
import com.example.apple.popularmoviesstageone.MovieDetails.MovieDetailsActivity;
import com.example.apple.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Result> resultList;
    private Context con;

    public MoviesAdapter(Context context, List<Result> resultsArrayList) {
        this.resultList = resultsArrayList;
        this.con = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {

        Result result = resultList.get(position);

        holder.titleTv.setText(result.getTitle());

        if (!result.getPosterPath().isEmpty()) {
            Picasso.get().load(Constants.IMAGE_BASE_URL + result.getPosterPath()).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_iv)
        ImageView imageView;
        @BindView(R.id.movie_title_tv)
        TextView titleTv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Result result = resultList.get(position);

            result = new Result(result.getVoteAverage(), result.getTitle(), result.getPosterPath(), result.getReleaseDate(), result.getOverview());

            con.startActivity(new Intent(con, MovieDetailsActivity.class).putExtra(Constants.MOVIE_OBJECT, result));

        }
    }
}
