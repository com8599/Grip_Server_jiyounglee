package com.grip.demo.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DownloadFileRepository extends JpaRepository<DownloadFile, Long> {
    Optional<DownloadFile> findByIdAndStatusLessThan(Long id, int status);

    List<DownloadFile> findAllByStatusLessThan(int status, Pageable pageable);
}
