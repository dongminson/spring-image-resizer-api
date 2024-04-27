package com.dms.springimageresizerapi.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  public byte[] resizeImage(MultipartFile file, int width, int height)
    throws IOException {
    if (!isImage(file)) {
      throw new IllegalArgumentException("Unsupported file type");
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String format = getFileExtension(file);
    Thumbnails.of(file.getInputStream())
      .size(width, height)
      .outputFormat(format)
      .toOutputStream(baos);
    return baos.toByteArray();
  }

  public boolean isImage(MultipartFile file) {
    try {
      ImageIO.read(file.getInputStream());
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public String getFileExtension(MultipartFile file) {
    String[] parts = file.getOriginalFilename().split("\\.");
    return parts[parts.length - 1];
  }
}
