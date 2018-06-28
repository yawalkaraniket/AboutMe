package com.aboutme.avenjr.aboutme.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AboutMeApi {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
