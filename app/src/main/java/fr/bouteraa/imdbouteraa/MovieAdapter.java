package fr.bouteraa.imdbouteraa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.bouteraa.imdbouteraa.models.Movie;

/**
 * Created by Félix on 26/11/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context mContext;
    private List<Movie> movies;
    private int positionMovie;


    public MovieAdapter(Context context, List<Movie> movie) {
        mContext = context;
        movies = movie;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie, viewGroup, false);

        final MovieViewHolder holder = new MovieViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                positionMovie=holder.getLayoutPosition();
                final Intent intent;

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        final Movie movie = movies.get(position);

        holder.titre.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());

        Picasso.with(mContext).load(movie.getBackdrop()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}