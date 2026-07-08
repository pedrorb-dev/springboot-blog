package com.project.blog.springbootblog.services;

import com.project.blog.springbootblog.entities.Post;
import com.project.blog.springbootblog.entities.Profile;
import com.project.blog.springbootblog.entities.User;
import com.project.blog.springbootblog.exceptions.NoEntityFound;
import com.project.blog.springbootblog.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new NoEntityFound("No hay un usuario con ese id"));
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        repository.deleteById(user.getId());
    }

    @Transactional
    @Override
    public User updateById(Long id, User user) {
        User userDB = findById(id);

        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());

        return repository.save(userDB);
    }
    @Transactional
    @Override
    public User addPost(Long id, Post post) {
        User userDB = findById(id);
        userDB.addPost(post);
        return userDB;
    }

    @Transactional
    @Override
    public User addProfile(Long id, Profile profile) {
        User userDB = findById(id);
        userDB.setProfile(profile);
        return userDB;
    }
}
