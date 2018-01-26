package com.android.ahivaran.data.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class PhotosEntity {
    @SerializedName("page")
    private String currentPage;
    @SerializedName("pages")
    private String pages;
    @SerializedName("perpage")
    private String perPage;
    @SerializedName("total")
    private String total;

    @SerializedName("photo")
    private List<PhotoEntity> photoEntityList;
}
