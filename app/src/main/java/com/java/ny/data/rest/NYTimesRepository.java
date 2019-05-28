package com.java.ny.data.rest;

import com.java.ny.data.model.NYTimesListVO;
import com.java.ny.data.model.NYTimesVO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class NYTimesRepository {

    private final NYTimeService repoService;

    @Inject
    public NYTimesRepository(NYTimeService repoService) {
        this.repoService = repoService;
    }

    public Single<NYTimesListVO> getMostPopularNews(String keyName) {
        return repoService.getMostPopularNews(keyName);
    }

    public Single<NYTimesVO> getNYTime(String owner, String name) {
        return repoService.getNYTime(owner, name);
    }
}
