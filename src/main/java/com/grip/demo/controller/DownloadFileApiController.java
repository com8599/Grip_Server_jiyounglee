package com.grip.demo.controller;

import com.grip.demo.dto.DownloadFileResponseDto;
import com.grip.demo.dto.DownloadFileSaveRequestDto;
import com.grip.demo.dto.DownloadFileUpdateRequestDto;
import com.grip.demo.service.DownloadFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/download/files")
@Tag(name = "이미지 스크랩 서비스 - 게시물 api")
public class DownloadFileApiController {
    private final DownloadFileService downloadFileService;

    public DownloadFileApiController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @PostMapping
    @Operation(summary = "게시물 추가")
    public ResponseEntity<DownloadFileResponseDto> save(@RequestBody DownloadFileSaveRequestDto requestDto) {
        DownloadFileResponseDto downloadFile = downloadFileService.saveDownloadFile(requestDto);
        return ResponseEntity.created(URI.create("/api/v1/download/files/" + downloadFile.getId())).body(downloadFile);
    }

    @PutMapping("{id}")
    @Operation(summary = "게시물 수정")
    public ResponseEntity update(@PathVariable Long id, @RequestBody DownloadFileUpdateRequestDto requestDto) {
        downloadFileService.updateDownloadFile(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "게시물 삭제")
    public ResponseEntity remove(@PathVariable Long id) {
        downloadFileService.removeDownloadFile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("list")
    @Operation(summary = "게시물 가져오기(페이지별로 첫페이지=0)")
    public ResponseEntity<List<DownloadFileResponseDto>> findAllDownloadFiles(@RequestParam int page) {
        return ResponseEntity.ok(downloadFileService.findDownloadFilesResponses(page));
    }

    @GetMapping("{id}")
    @Operation(summary = "게시물 가져오기(단일)&읽기")
    public ResponseEntity<DownloadFileResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(downloadFileService.findById(id));
    }
}
