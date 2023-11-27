package com.example.imagestorage.data;

import com.example.imagestorage.model.Image;

import java.util.List;

public interface ImgDao {

    void save(Image image);
    Image findByName(String name);
    List<String> retrieveImageNames();
}
