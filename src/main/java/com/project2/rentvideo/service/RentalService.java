package com.project2.rentvideo.service;

import com.project2.rentvideo.model.*;
import com.project2.rentvideo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository,
                         VideoRepository videoRepository,
                         UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    public Rental rentVideo(Long videoId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        List<Rental> activeRentals = rentalRepository.findByUserAndReturnedFalse(user);

        if (activeRentals.size() >= 2) {
            throw new RuntimeException("User already has 2 active rentals");
        }

        if (!video.isAvailable()) {
            throw new RuntimeException("Video not available");
        }

        video.setAvailable(false);

        Rental rental = new Rental(user, video);

        videoRepository.save(video);

        return rentalRepository.save(rental);
    }

    public Rental returnVideo(Long videoId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Rental> rentals = rentalRepository.findByUserAndReturnedFalse(user);

        Rental rental = rentals.stream()
                .filter(r -> r.getVideo().getId().equals(videoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setReturned(true);

        Video video = rental.getVideo();
        video.setAvailable(true);

        videoRepository.save(video);

        return rentalRepository.save(rental);
    }
}