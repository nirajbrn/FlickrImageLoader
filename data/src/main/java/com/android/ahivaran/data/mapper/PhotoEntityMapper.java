package com.android.ahivaran.data.mapper;


import com.android.ahivaran.data.entity.PhotoEntity;
import com.android.ahivaran.data.entity.PhotosEntity;
import com.android.ahivaran.domain.Photo;
import com.android.ahivaran.domain.Photos;

import java.util.ArrayList;
import java.util.List;

public class PhotoEntityMapper {

    public Photos transform (PhotosEntity photosEntity) {
        List<PhotoEntity> photoEntityList = photosEntity.getPhotoEntityList();
        List<Photo> photoList = new ArrayList<>();
        for (PhotoEntity photoEntity : photoEntityList) {
            photoList.add(new Photo(photoEntity.getId(), photoEntity.getOwner(), photoEntity.getSecret(), photoEntity.getServer()));
        }

        return new Photos(photosEntity.getCurrentPage(), photosEntity.getPages(),
                photosEntity.getPerPage(), photosEntity.getTotal(), photoList);
    }
}
