package com.project.blog.springbootblog.controllers;

import com.project.blog.springbootblog.entities.Post;
import com.project.blog.springbootblog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<Post> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Post post, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post postDB = service.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody Post post, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        Post postDB = service.updateById(id, post);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postDB);
    }

    private ResponseEntity<?> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
