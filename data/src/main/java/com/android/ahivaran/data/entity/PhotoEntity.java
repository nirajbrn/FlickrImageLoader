package com.android.ahivaran.data.entity;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class PhotoEntity {
    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
}
