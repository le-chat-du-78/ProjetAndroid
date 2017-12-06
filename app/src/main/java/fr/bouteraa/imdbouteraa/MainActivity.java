package fr.bouteraa.imdbouteraa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import fr.bouteraa.imdbouteraa.models.Movie;
import fr.bouteraa.imdbouteraa.service.ApiServiceImpl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ApiServiceImpl apiService = new ApiServiceImpl();
    public String affichage;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getPopularMovies("liste");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_films) {

        } else if (id == R.id.nav_series) {
            Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            switch (item.getItemId()) {
                case R.id.liste:
                    affichage="liste";
                    getPopularMovies(affichage);
                    return true;
                case R.id.images:
                    affichage="images";
                    getPopularMovies(affichage);
                    return true;
                case R.id.description:
                    affichage="description";
                    getPopularMovies(affichage);
                    return true;
                case R.id.nav_films:
                    Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();


                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                case R.id.nav_series:


                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                case R.id.nav_settings:


                    drawer.closeDrawer(GravityCompat.START);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_big) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getPopularMovies(final String affichage) {
        apiService.getPopularMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                afficher(affichage, movies);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void afficher(String affichage, List<Movie> movies) {

        RecyclerView RV_movies = (RecyclerView) findViewById(R.id.RV_movies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        RV_movies.setLayoutManager(layoutManager);

        if (affichage == "liste"){
            MovieAdapterListe listeAdapter = new MovieAdapterListe(getApplicationContext(), movies);
            RV_movies.setAdapter(listeAdapter);
        }else if(affichage == "images"){
            MovieAdapterImages imageAdapter = new MovieAdapterImages(getApplicationContext(), movies);
            RV_movies.setAdapter(imageAdapter);
        }
        else if(affichage == "description"){
            MovieAdapterDescription descriptionAdapter = new MovieAdapterDescription(getApplicationContext(), movies);
            RV_movies.setAdapter(descriptionAdapter);

        }
    }


}
