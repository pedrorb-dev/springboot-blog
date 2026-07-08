package com.project.blog.springbootblog.services;

import com.project.blog.springbootblog.entities.Profile;

import java.util.List;

public interface ProfileService {
    Profile save(Profile profile);
    List<Profile> findAll();
    Profile findById(Long id);
    void deleteById(Long id);
    Profile updateById(Long id, Profile profile);
}
