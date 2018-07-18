package com.aboutme.avenjr.aboutme.data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aboutme.avenjr.aboutme.Adapter.RecyclerViewAdapterExample.BASE_URL;

public class apiUtil {

    private static Retrofit retrofit = null;
    private final static String API_KEY = "3f2700414c5e5e2a5fef77bb92707835";

    private static AboutMeApi retrofit() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()

                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

        }

        return retrofit.create(AboutMeApi.class);
    }
    public static Call<MovieResponse> getBaseUri(){
        return retrofit().getTopRatedMovies(API_KEY);
    }
}
