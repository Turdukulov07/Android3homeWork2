package com.example.andr3lesson2.data.remote;

import com.example.andr3lesson2.data.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {

    @GET(Keys.KEY_FILMS)
    Call<List<Film>> getAllFilms();

    @GET(Keys.KEY_FILMS_ID)
    Call<Film> getFilmId(@Path("id") String id);
}
