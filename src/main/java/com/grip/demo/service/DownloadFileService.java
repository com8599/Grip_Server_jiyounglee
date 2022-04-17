package com.grip.demo.service;

import com.grip.demo.domain.DownloadFile;
import com.grip.demo.domain.DownloadFileRepository;
import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class DownloadFileService {
    private final DownloadFileRepository downloadFileRepository;
    private final FileUploader fileUploader;

    public DownloadFileService(DownloadFileRepository downloadFileRepository, FileUploader fileUploader) {
        this.downloadFileRepository = downloadFileRepository;
        this.fileUploader = fileUploader;
    }

    public DownloadFileResponseDto saveDownloadFile(DownloadFileSaveRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.save(requestDto.toEntity(fileUploader.upload(requestDto.getLink())));
        return DownloadFileResponseDto.of(downloadFile, "");
    }
}
