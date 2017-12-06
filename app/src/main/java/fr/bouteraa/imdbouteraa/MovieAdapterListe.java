package fr.bouteraa.imdbouteraa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.bouteraa.imdbouteraa.models.Movie;

/**
 * Created by Félix on 06/12/2017.
 */

public class MovieAdapterListe extends RecyclerView.Adapter<MovieViewHolder> {

    private Context mContext;
    private List<Movie> movies;
    private int positionMovie;


    public MovieAdapterListe(Context context, List<Movie> movie) {
        mContext = context;
        movies = movie;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_liste, viewGroup, false);

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

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                Bundle extras = new Bundle();
                extras.putString(MovieActivity.EXTRA_MOVIE_TITRE,movie.getTitle());
                extras.putString(MovieActivity.EXTRA_MOVIE_DESCRIPTION, movie.getDescription());
                extras.putString(MovieActivity.EXTRA_MOVIE_IMAGE, movie.getBackdrop());

                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
        holder.titre.setText(String.valueOf(movie.getTitle()));
        Picasso.with(mContext).load(movie.getPoster()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}