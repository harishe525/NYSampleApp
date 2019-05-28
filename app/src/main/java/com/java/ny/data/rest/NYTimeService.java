package com.java.ny.data.rest;

import com.java.ny.data.model.NYTimesListVO;
import com.java.ny.data.model.NYTimesVO;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTimeService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    Single<NYTimesListVO> getMostPopularNews(@Query("api-key") String apiKey);

    @GET("repos/{owner}/{name}")
    Single<NYTimesVO> getNYTime(@Path("owner") String owner, @Path("name") String name);
}
