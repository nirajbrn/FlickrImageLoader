package com.android.ahivaran.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class Photos {
    private String currentPage;
    private String pages;
    private String perPage;
    private String total;

    private List<Photo> photoList;
}
