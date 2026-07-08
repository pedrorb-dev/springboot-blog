package com.project.blog.springbootblog.services;

import com.project.blog.springbootblog.entities.Profile;
import com.project.blog.springbootblog.exceptions.NoEntityFound;
import com.project.blog.springbootblog.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository repository;

    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }
    @Transactional
    @Override
    public Profile save(Profile profile) {
        return repository.save(profile);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Profile> findAll() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Profile findById(Long id) {
        Optional<Profile> optionalProfile = repository.findById(id);
        return optionalProfile.orElseThrow(() -> new NoEntityFound("No hay un profile con ese id"));
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Profile profile = findById(id);
        repository.deleteById(profile.getId());
    }
    @Transactional
    @Override
    public Profile updateById(Long id, Profile profile) {
        Profile profileDB = findById(id);

        profileDB.setBiography(profile.getBiography());
        profileDB.setUrlImage(profile.getUrlImage());

        return repository.save(profileDB);
    }
}
