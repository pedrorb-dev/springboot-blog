package com.project.blog.springbootblog.services;

import com.project.blog.springbootblog.entities.Post;

import java.util.List;

public interface PostService {
    Post save(Post post);
    List<Post> findAll();
    Post findById(Long id);
    void deleteById(Long id);
    Post updateById(Long id, Post post);
}
