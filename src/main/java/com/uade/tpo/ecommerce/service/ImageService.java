package com.uade.tpo.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Image;
import com.uade.tpo.ecommerce.repository.ImageRepository;

@Service
public class ImageService implements IImageService {
  @Autowired
  private ImageRepository imageRepository;

  @Override
  public Image uploadImage(Image image) {
    return imageRepository.save(image);
  }

  @Override
  public Image getImageById(long id) {
    return imageRepository.findById(id).get();
  }
}
