package com.grip.demo.dto;

import com.grip.demo.domain.DownloadFile;

public class DownloadFileSaveRequestDto {
    private Long accountId;
    private String link;
    private String title;
    private String body;

//    public DownloadFileSaveRequestDto() {
//    }
//
    public DownloadFileSaveRequestDto(Long accountId, String link, String title, String body) {
        this.accountId = accountId;
        this.link = link;
        this.title = title;
        this.body = body;
    }

    public DownloadFile toEntity(String directory) {
        return new DownloadFile(accountId, link, title, body, directory);
    }

    public String getLink() {
        return link;
    }
}
