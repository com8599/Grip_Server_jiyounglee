package com.grip.demo.service;

import com.grip.demo.domain.DownloadFile;
import com.grip.demo.domain.DownloadFileRepository;
import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.dto.DownloadFileUpdateRequestDto;
import com.grip.demo.enumclass.StatusKind;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadFileService {
    private final DownloadFileRepository downloadFileRepository;
    private final FileUploader fileUploader;

    @Value("${default.value.page}")
    private int defaultPage;

    public DownloadFileService(DownloadFileRepository downloadFileRepository, FileUploader fileUploader) {
        this.downloadFileRepository = downloadFileRepository;
        this.fileUploader = fileUploader;
    }

    public DownloadFileResponseDto saveDownloadFile(DownloadFileSaveRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.save(requestDto.toEntity(fileUploader.upload(requestDto.getLink())));
        return DownloadFileResponseDto.of(downloadFile);
    }

    @Transactional
    @CachePut(value = "files", key = "#id")
    public void updateDownloadFile(Long id, DownloadFileUpdateRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.update(new DownloadFile(requestDto.getTitle(), requestDto.getBody()));
    }

    @Transactional
    @CacheEvict(value = "files", key = "#id")
    public void removeDownloadFile(Long id) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.delete();
    }

    public List<DownloadFileResponseDto> findDownloadFilesResponses(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPage);
        List<DownloadFile> downloadFiles = downloadFileRepository.findAllByStatusLessThan(StatusKind.DELETE.getId(), pageRequest);
        return downloadFiles.stream()
                .map(DownloadFileResponseDto::of)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "files", key = "#id")
    public DownloadFileResponseDto findById(Long id) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.increaseHit();
        return DownloadFileResponseDto.of(downloadFile);
    }
}
