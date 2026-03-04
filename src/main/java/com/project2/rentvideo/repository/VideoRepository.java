package com.project2.rentvideo.repository;

import com.project2.rentvideo.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}