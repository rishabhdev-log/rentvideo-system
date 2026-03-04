package com.project2.rentvideo.controller;

import com.project2.rentvideo.model.Rental;
import com.project2.rentvideo.service.RentalService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/{videoId}/rent")
    public Rental rentVideo(@PathVariable Long videoId, Authentication authentication) {

        String email = authentication.getName();

        return rentalService.rentVideo(videoId, email);
    }

    @PostMapping("/{videoId}/return")
    public Rental returnVideo(@PathVariable Long videoId, Authentication authentication) {

        String email = authentication.getName();

        return rentalService.returnVideo(videoId, email);
    }
}