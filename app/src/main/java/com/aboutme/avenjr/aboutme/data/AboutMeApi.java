package com.aboutme.avenjr.aboutme.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AboutMeApi {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @Headers("user-key:6fb64400590cfb0701dfa84a77ea1787")
    @GET("categories")
    Call<ZomatoApiResponse> getRestaurants();

}
