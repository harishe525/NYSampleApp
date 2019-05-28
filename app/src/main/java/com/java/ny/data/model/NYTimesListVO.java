package com.java.ny.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NYTimesListVO {
    @SerializedName("results")
    private List<NYTimesVO> nyTimesVOS;

    public List<NYTimesVO> getNyTimesVOS() {
        return nyTimesVOS;
    }

    public void setNyTimesVOS(List<NYTimesVO> nyTimesVOS) {
        this.nyTimesVOS = nyTimesVOS;
    }
}
