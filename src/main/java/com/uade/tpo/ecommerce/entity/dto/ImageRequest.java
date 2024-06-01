package com.uade.tpo.ecommerce.entity.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageRequest {
    private String name;
    private MultipartFile file;
}
