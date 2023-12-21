package com.blog.services.impl;

import com.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String imagePath, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String randomFileId = UUID.randomUUID().toString();
        String name = randomFileId.concat(fileName.substring(fileName.lastIndexOf(".")));

        //FullPath
        String filePath = imagePath + File.separator + name;

        //create folder if not created
        File myFile = new File(imagePath);
        if(!myFile.exists()){
            myFile.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(),Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String imagePath, String fileName) throws FileNotFoundException {
       String fullPath = imagePath + File.separator + fileName;
       InputStream xStream = new FileInputStream(fullPath);
       return xStream;
    }
}
