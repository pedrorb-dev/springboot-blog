package com.project.blog.springbootblog.services;
import com.project.blog.springbootblog.entities.Post;
import com.project.blog.springbootblog.entities.Profile;
import com.project.blog.springbootblog.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User updateById(Long id, User user);
    User addPost(Long id, Post post);
    User addProfile(Long id, Profile profile);
}
