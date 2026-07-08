package com.project.blog.springbootblog.repositories;

import com.project.blog.springbootblog.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
