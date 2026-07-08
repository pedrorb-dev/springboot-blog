package com.project.blog.springbootblog.controllers;

import com.project.blog.springbootblog.entities.Post;
import com.project.blog.springbootblog.entities.Profile;
import com.project.blog.springbootblog.entities.User;
import com.project.blog.springbootblog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<User> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User userDB = service.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        User userDB = service.updateById(id, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDB);
    }

    @PatchMapping("/post/{id}")
    public ResponseEntity<?> addPost(@PathVariable Long id, @Valid @RequestBody Post post, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        User userDB = service.addPost(id, post);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDB);
    }

    @PatchMapping("/profile/{id}")
    public ResponseEntity<?> addProfile(@PathVariable Long id, @Valid @RequestBody Profile profile, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        User userDB = service.addProfile(id, profile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDB);
    }

    private ResponseEntity<?> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
