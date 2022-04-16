package com.grip.demo.controller;

import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.service.DownloadFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/download/files")
public class DownloadFileApiController {
    private final DownloadFileService downloadFileService;

    public DownloadFileApiController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @PostMapping()
    public ResponseEntity<DownloadFileResponseDto> save(@RequestBody DownloadFileSaveRequestDto requestDto) throws IOException {
        DownloadFileResponseDto downloadFile = downloadFileService.saveDownloadFile(requestDto);
        return ResponseEntity.created(URI.create("/api/v1/download/files/" + downloadFile.getId())).body(downloadFile);
    }
}
