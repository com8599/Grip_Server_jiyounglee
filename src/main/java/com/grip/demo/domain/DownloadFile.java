package com.grip.demo.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
public class DownloadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int status;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String directory;

    private long hit;

    public DownloadFile(Long accountId, String link, String title, String body, String directory) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = 0;
        this.accountId = accountId;
        this.link = link;
        this.title = title;
        this.body = body;
        this.directory = directory;
        this.hit = 0;
    }

    public DownloadFile() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = 0;
        this.accountId = 0L;
        this.link = "";
        this.title = "";
        this.body = "";
        this.directory = "";
        this.hit = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
