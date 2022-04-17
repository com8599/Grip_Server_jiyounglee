package com.grip.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUploader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String OUTPUT_FILE_PATH = "/img/";
    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(String path) throws IOException {
        Files.delete(new File(path).toPath());
    }

    // 로컬에 파일 업로드 하기
    public String upload(String link) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());
        File convertFile = new File(System.getProperty("user.dir") + OUTPUT_FILE_PATH + currentDate);
        // not exist the root, mkdir the root
        if (!convertFile.exists() && !convertFile.mkdirs()) {
            logger.error("fail to mkdir");
        }
        String outputDir = System.getProperty("user.dir") + OUTPUT_FILE_PATH + currentDate;
        InputStream is = null;
        FileOutputStream os = null;
        try{
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();

            logger.info("responseCode " + responseCode);

            // Status 가 200 일 때
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = conn.getHeaderField("Content-Disposition");
                String contentType = conn.getContentType();

                // 일반적으로 Content-Disposition 헤더에 있지만
                // 없을 경우 url 에서 추출해 내면 된다.
                if (disposition != null) {
                    String target = "filename=";
                    int index = disposition.indexOf(target);
                    if (index != -1) {
                        fileName = disposition.substring(index + target.length() + 1);
                    }
                } else {
                    fileName = link.substring(link.lastIndexOf("/") + 1);
                }

                logger.info("Content-Type = " + contentType
                        + ", Content-Disposition = " + disposition
                        + ", fileName = " + fileName);

                is = conn.getInputStream();
                File file = new File(outputDir, UUID.randomUUID() + fileName);
                os = new FileOutputStream(file);

                final int BUFFER_SIZE = 4096;
                int bytesRead;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
                logger.info("File downloaded");
                return file.getAbsolutePath();
            } else {
                logger.error("No file to download. Server replied HTTP code: " + responseCode);
            }
            conn.disconnect();
        } catch (Exception e){
            logger.error("An error occurred while trying to download a file.");
            e.printStackTrace();
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
        return "";
    }
}
