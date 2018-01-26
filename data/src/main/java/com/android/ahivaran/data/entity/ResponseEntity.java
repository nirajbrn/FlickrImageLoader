package com.android.ahivaran.data.entity;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class ResponseEntity {
    @SerializedName("stat")
    private String status;
    @SerializedName("photos")
    private PhotosEntity photos;
}
