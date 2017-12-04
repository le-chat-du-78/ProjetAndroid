package fr.bouteraa.imdbouteraa;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Félix on 26/11/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView titre;
        TextView description;
        ImageView image;

public MovieViewHolder(View itemViewUser) {
        super(itemViewUser);

        titre = (TextView) itemView.findViewById(R.id.movie_name);
        description = (TextView) itemView.findViewById(R.id.movie_description);
        image = (ImageView) itemView.findViewById(R.id.movie_picture);
        }
}
