package fr.bouteraa.imdbouteraa.service;

/**
 * Created by ko on 15/11/2017.
 */

import fr.bouteraa.imdbouteraa.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static fr.bouteraa.imdbouteraa.models.Movie.*;

public interface ApiService {
    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String apiKey);

}