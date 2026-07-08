package com.project.blog.springbootblog.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String biography;

    private LocalDate createdAt;
    private String urlImage;
    @JsonIgnoreProperties("profile")
    @OneToOne(mappedBy = "profile")
    private User user;

    @PrePersist
    public void saveEntity() {
        this.createdAt = LocalDate.now();
    }

    public Profile() {
    }

    public Profile(Long id, String biography, LocalDate createdAt, String urlImage, User user) {
        this.id = id;
        this.biography = biography;
        this.createdAt = createdAt;
        this.urlImage = urlImage;
        this.user = user;
    }

    public Profile(String biography, String urlImage) {
        this.biography = biography;
        this.urlImage = urlImage;
    }

    public Profile(String urlImage, String biography, User user) {
        this.urlImage = urlImage;
        this.biography = biography;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
