package com.project2.rentvideo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    private LocalDateTime rentedAt;

    private boolean returned;

    public Rental() {}

    public Rental(User user, Video video) {
        this.user = user;
        this.video = video;
        this.rentedAt = LocalDateTime.now();
        this.returned = false;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Video getVideo() {
        return video;
    }

    public LocalDateTime getRentedAt() {
        return rentedAt;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public void setRentedAt(LocalDateTime rentedAt) {
        this.rentedAt = rentedAt;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}