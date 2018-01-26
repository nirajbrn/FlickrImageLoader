package com.android.ahivaran.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class FlickrImage {
    private String status;
    private Photo photos;
}
