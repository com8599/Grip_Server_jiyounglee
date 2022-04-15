package com.grip.demo.service;

import com.grip.demo.domain.DownloadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadFileService {
    private final DownloadFileRepository downloadFileRepository;
}
