package com.example.imagestorage.service;

import com.example.imagestorage.data.ImgDao;
import com.example.imagestorage.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImgDao imageDao;

    public String uploadImage(MultipartFile imageFile) throws IOException {

        Image image = new Image();
        image.setName(imageFile.getOriginalFilename());
        image.setType(imageFile.getContentType());
        image.setData(imageFile.getBytes());

        this.imageDao.save(image);
        return imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {

        return this.imageDao.findByName(imageName).getData();
    }

    public List<String> retrieveAllImages() {
        return this.imageDao.retrieveImageNames();
    }
}
