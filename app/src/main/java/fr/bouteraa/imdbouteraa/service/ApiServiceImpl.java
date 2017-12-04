package fr.bouteraa.imdbouteraa.service;

import java.util.ArrayList;
import java.util.List;

import fr.bouteraa.imdbouteraa.models.Movie;
import fr.bouteraa.imdbouteraa.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static fr.bouteraa.imdbouteraa.utils.Constants.API_KEY;
import static fr.bouteraa.imdbouteraa.utils.Constants.BASE_URL;

/**
 * Created by adrien on 01/09/2017.
 */

public class ApiServiceImpl {

    public static ApiService getMovieApiService() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(ApiService.class);
    }


    public void getPopularMovies(final CustomCallBack<Movie> customCallBack) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getPopularMovies(API_KEY).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films populaires");
            }
        });
    }


    public interface CustomCallBack<T> {
        void onSuccess(List<T> movies);

        void onError(String message);
    }
}
