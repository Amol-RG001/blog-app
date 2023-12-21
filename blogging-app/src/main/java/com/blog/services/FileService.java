package com.blog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(String imagePath, MultipartFile file) throws IOException;
    InputStream getResource(String imagePath, String fileName) throws FileNotFoundException;
}
