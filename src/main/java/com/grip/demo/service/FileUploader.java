package com.grip.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class FileUploader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String OUTPUT_FILE_PATH = "/img/";
    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) throws IOException {
        Files.delete(targetFile.toPath());
    }

    // 로컬에 파일 업로드 하기
    public Optional<Path> upload(String link) throws IOException {
        Path imagePath;
        try(InputStream in = new URL(link).openStream()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = simpleDateFormat.format(new Date());
            File convertFile = new File(System.getProperty("user.dir") + OUTPUT_FILE_PATH + currentDate);
            // not exist the root, mkdir the root
            if (!convertFile.exists() && !convertFile.mkdirs()) {
                logger.info("fail to mkdir");
            }
            imagePath = Paths.get(System.getProperty("user.dir") + OUTPUT_FILE_PATH + currentDate + "/");
            Files.copy(in, imagePath);
        }
        return Optional.of(imagePath);
    }
}
