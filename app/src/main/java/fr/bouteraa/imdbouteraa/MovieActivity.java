package fr.bouteraa.imdbouteraa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE_TITRE ="EXTRA_MOVIE_TITRE";
    public static String EXTRA_MOVIE_DESCRIPTION ="EXTRA_MOVIE_DESCRIPTION";
    public static String EXTRA_MOVIE_IMAGE ="EXTRA_MOVIE_IMAGE";

    TextView m_titre;
    TextView m_description;
    ImageView m_image;

    public String titre;
    public String description;
    public String image;
    public String rating;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ajouté aux favoris", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        titre = extras.getString(EXTRA_MOVIE_TITRE);
        description = extras.getString(EXTRA_MOVIE_DESCRIPTION);
        image = extras.getString(EXTRA_MOVIE_IMAGE);

        affichage(titre, description, image);

        setTitle(titre);

    }


    public void affichage(String t ,String d,String i){

        m_titre = (TextView) this.findViewById(R.id.titre);
        m_description = (TextView) this.findViewById(R.id.description);
        m_image= (ImageView) this.findViewById(R.id.image);


        m_titre.setText(t);
        m_description.setText(d);
        Picasso.with(mContext).load(i).into(m_image);


    }
}
