package com.uade.tpo.ecommerce.controllers.admin;

import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Image;
import com.uade.tpo.ecommerce.entity.dto.ImageRequest;
import com.uade.tpo.ecommerce.entity.dto.ImageResponse;
import com.uade.tpo.ecommerce.service.IImageService;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("admin/image")
public class AdminImageController {
  @Autowired
  private IImageService imageService;

  @PostMapping()
  public ResponseEntity<Object> uploadImage(ImageRequest request) throws IOException, SerialException, SQLException {
    byte[] bytes = request.getFile().getBytes();
    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
    Image newImage = Image.builder().image(blob).name(request.getName()).build();
    Image img = imageService.uploadImage(newImage);
    Object imgResponse = Image.builder().id(img.getId()).name(img.getName()).date(img.getDate()).build();
    return ResponseEntity.ok().body(imgResponse);
  }

  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<ImageResponse> getImageById(@PathVariable Long id) throws IOException, SQLException {
    Image image = imageService.getImageById(id);
    String encodedString = Base64.getEncoder()
        .encodeToString(image.getImage().getBytes(1, (int) image.getImage().length()));
    return ResponseEntity.ok().body(ImageResponse.builder().file(encodedString).id(id).build());
  }

}
