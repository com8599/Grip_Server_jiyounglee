package com.grip.demo.controller;

import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.dto.DownloadFileUpdateRequestDto;
import com.grip.demo.service.DownloadFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/download/files")
public class DownloadFileApiController {
    private final DownloadFileService downloadFileService;

    public DownloadFileApiController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @PostMapping
    public ResponseEntity<DownloadFileResponseDto> save(@RequestBody DownloadFileSaveRequestDto requestDto) {
        DownloadFileResponseDto downloadFile = downloadFileService.saveDownloadFile(requestDto);
        return ResponseEntity.created(URI.create("/api/v1/download/files/" + downloadFile.getId())).body(downloadFile);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody DownloadFileUpdateRequestDto requestDto) {
        downloadFileService.updateDownloadFile(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        downloadFileService.removeDownloadFile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("list")
    public ResponseEntity<List<DownloadFileResponseDto>> findAllDownloadFiles(@RequestParam int page) {
        return ResponseEntity.ok(downloadFileService.findDownloadFilesResponses(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<DownloadFileResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(downloadFileService.findById(id));
    }
}
