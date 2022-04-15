package com.grip.demo.controller;

import com.grip.demo.service.DownloadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DownloadFileApiController {
    private final DownloadFileService downloadFileService;
}
