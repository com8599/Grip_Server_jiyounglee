package com.grip.demo.service;

import com.grip.demo.domain.DownloadFile;
import com.grip.demo.domain.DownloadFileRepository;
import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.dto.DownloadFileUpdateRequestDto;
import com.grip.demo.enumclass.StatusKind;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateDownloadFile(Long id, DownloadFileUpdateRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.update(new DownloadFile(requestDto.getTitle(), requestDto.getBody()));
    }
}
