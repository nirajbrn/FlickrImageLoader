package com.android.ahivaran.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Photo {
    private String id;
    private String owner;
    private String secret;
    private String server;
}
