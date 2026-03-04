package com.project2.rentvideo.repository;

import com.project2.rentvideo.model.Rental;
import com.project2.rentvideo.model.User;
import com.project2.rentvideo.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserAndReturnedFalse(User user);

    long countByUserAndReturnedFalse(User user);

    Optional<Rental> findByUserAndVideoAndReturnedFalse(User user, Video video);
}