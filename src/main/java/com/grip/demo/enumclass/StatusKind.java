package com.grip.demo.enumclass;


public enum StatusKind {
    NORMAL(0,"일반 상태","일반 상태"),
    READED(1,"읽은 상태","읽은 상태"),
    DELETE(100,"삭제","삭제")
    ;

    private Integer id;
    private String title;
    private String description;

    StatusKind(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
