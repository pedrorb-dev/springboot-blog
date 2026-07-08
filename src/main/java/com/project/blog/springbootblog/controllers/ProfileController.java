package com.project.blog.springbootblog.controllers;

import com.project.blog.springbootblog.entities.Profile;
import com.project.blog.springbootblog.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<Profile> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Profile profile, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile profileDB = service.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @Valid @RequestBody Profile profile, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validate(result);
        }
        Profile profileDB = service.updateById(id, profile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileDB);
    }

    private ResponseEntity<?> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
