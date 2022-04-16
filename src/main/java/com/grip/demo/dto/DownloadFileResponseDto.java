package com.grip.demo.dto;

import com.grip.demo.domain.DownloadFile;

import javax.persistence.Column;
import java.util.Date;

public class DownloadFileResponseDto {

    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private int status;
    private Long accountId;
    private String accountName;
    private String link;
    private String title;
    private String body;
    private String directory;
    private long hit;

    public DownloadFileResponseDto(DownloadFile downloadFile, String accountName) {
        this.id = downloadFile.getId();
        this.createdAt = downloadFile.getCreatedAt();
        this.updatedAt = downloadFile.getUpdatedAt();
        this.status = downloadFile.getStatus();
        this.accountId = downloadFile.getAccountId();
        this.accountName = accountName;
        this.link = downloadFile.getLink();
        this.title = downloadFile.getTitle();
        this.body = downloadFile.getBody();
        this.directory = downloadFile.getDirectory();
        this.hit = downloadFile.getHit();
    }

    public static DownloadFileResponseDto of(DownloadFile downloadFile, String accountName) {
        return new DownloadFileResponseDto(downloadFile, accountName);

    }

    public Long getId() {
        return id;
    }
}
