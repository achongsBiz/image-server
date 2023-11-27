package com.example.imagestorage.controller;

import com.example.imagestorage.model.ImageDTO;
import com.example.imagestorage.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private ImageService imageService;

    @Value("${url}")
    private String sourceUrl;

    @RequestMapping(path="/post", method= RequestMethod.POST)
    public ResponseEntity<ImageDTO> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImage = imageService.uploadImage(file);
        ImageDTO imageDTO = new ImageDTO( buildUrl(uploadImage) );

        return ResponseEntity.status(HttpStatus.CREATED).body(imageDTO);

    }

    /*
   This endpoint is the means by which a client can access any uploaded image.
   For example, an HTML document can have:
   <img src="{your-domain}/image/{your-image-name}/>
     */
    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {

        byte[] imageData = imageService.downloadImage(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);

    }

    @GetMapping("/all-images")
    public ResponseEntity<List<ImageDTO>> retrieveAllImages() {

        List<ImageDTO> imageNames = new ArrayList<>();

        for(String nameStr : imageService.retrieveAllImages()) {
            imageNames.add( new ImageDTO( buildUrl(nameStr)) );
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageNames);
    }

    private String buildUrl(String imageName) {
        return sourceUrl + "image/" + imageName;
    }
}
