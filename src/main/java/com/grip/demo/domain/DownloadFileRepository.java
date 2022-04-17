package com.grip.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DownloadFileRepository extends JpaRepository<DownloadFile, Long> {
    Optional<DownloadFile> findByIdAndStatusLessThan(Long id, int status);
}
