package com.grip.demo.dto;

import com.grip.demo.domain.DownloadFile;

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

    private static final String DEFAULT_ACCOUNT_NAME = "";
    // TODO: account 도메인 생성이 삭제될 항목

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

    public static DownloadFileResponseDto of(DownloadFile downloadFile) {
        return new DownloadFileResponseDto(downloadFile, DEFAULT_ACCOUNT_NAME);
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDirectory() {
        return directory;
    }

    public long getHit() {
        return hit;
    }
}
