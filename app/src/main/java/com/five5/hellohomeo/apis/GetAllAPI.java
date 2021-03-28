package com.five5.hellohomeo.apis;

import com.five5.hellohomeo.models.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllAPI {
    @GET("v4/crew")
    Call<List<Crew>> getAllCrewDetails();
}
