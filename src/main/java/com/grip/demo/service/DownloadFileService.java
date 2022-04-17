package com.grip.demo.service;

import com.grip.demo.domain.DownloadFile;
import com.grip.demo.domain.DownloadFileRepository;
import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.dto.DownloadFileUpdateRequestDto;
import com.grip.demo.enumclass.StatusKind;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadFileService {
    private final DownloadFileRepository downloadFileRepository;
    private final FileUploader fileUploader;

    private static final int DEFAULT_PAGE = 10;

    public DownloadFileService(DownloadFileRepository downloadFileRepository, FileUploader fileUploader) {
        this.downloadFileRepository = downloadFileRepository;
        this.fileUploader = fileUploader;
    }

    public DownloadFileResponseDto saveDownloadFile(DownloadFileSaveRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.save(requestDto.toEntity(fileUploader.upload(requestDto.getLink())));
        return DownloadFileResponseDto.of(downloadFile);
    }

    @Transactional
    public void updateDownloadFile(Long id, DownloadFileUpdateRequestDto requestDto) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.update(new DownloadFile(requestDto.getTitle(), requestDto.getBody()));
    }

    @Transactional
    public void removeDownloadFile(Long id) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.delete();
    }

    public List<DownloadFileResponseDto> findDownloadFilesResponses(int page) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE);
        List<DownloadFile> downloadFiles = downloadFileRepository.findAllByStatusLessThan(StatusKind.DELETE.getId(), pageRequest);
        return downloadFiles.stream()
                .map(DownloadFileResponseDto::of)
                .collect(Collectors.toList());
    }

    public DownloadFileResponseDto findById(Long id) {
        DownloadFile downloadFile = downloadFileRepository.findByIdAndStatusLessThan(id, StatusKind.DELETE.getId()).orElseThrow(RuntimeException::new);
        downloadFile.increaseHit();
        return DownloadFileResponseDto.of(downloadFile);
    }
}
