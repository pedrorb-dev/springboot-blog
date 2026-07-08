package com.project.blog.springbootblog.services;

import com.project.blog.springbootblog.entities.Post;
import com.project.blog.springbootblog.exceptions.NoEntityFound;
import com.project.blog.springbootblog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService{
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return (List<Post>) repository.findAll();
    }

    @Override
    public Post findById(Long id) {
        List<Post> posts = repository.findAll();
        for(Post post: posts) {
            if(post.getId().equals(id)) {
                return post;
            }
        }
        throw  new NoEntityFound("No hay un post con ese id");
    }

    @Override
    public void deleteById(Long id) {
        Post post = findById(id);
        repository.deleteById(post.getId());
    }

    @Override
    public Post updateById(Long id, Post post) {
        Post postDB = findById(id);

        postDB.setTitle(post.getTitle());
        postDB.setContent(post.getContent());

        return repository.save(postDB);
    }
}
