package com.gaponec.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Convector {

    public static File convertFromMultipart(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

}
