package com.uade.tpo.ecommerce.service;

import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Image;

@Service
public interface IImageService {
    public Image uploadImage(Image image);
    public Image getImageById(long id);
}
