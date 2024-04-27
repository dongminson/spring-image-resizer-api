package com.dms.springimageresizerapi.controllers;

import com.dms.springimageresizerapi.services.ImageService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

  @Autowired
  private ImageService imageService;

  @PostMapping("/resize")
  public ResponseEntity<byte[]> resizeImage(
    @RequestParam("file") MultipartFile file,
    @RequestParam("width") int width,
    @RequestParam("height") int height
  ) {
    try {
      byte[] resizedImageBytes = imageService.resizeImage(file, width, height);
      String format = imageService.getFileExtension(file);

      return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("image/" + format))
        .body(resizedImageBytes);
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
