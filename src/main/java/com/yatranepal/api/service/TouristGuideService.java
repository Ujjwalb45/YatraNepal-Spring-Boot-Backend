package com.yatranepal.api.service;

import com.yatranepal.api.model.TouristGuide;
import com.yatranepal.api.repository.TouristGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristGuideService {

    @Autowired
    private TouristGuideRepository touristGuideRepository;

    public List<TouristGuide> getAllTouristGuides() {
        return touristGuideRepository.findAll();
    }

    public Optional<TouristGuide> getTouristGuideById(Long id) {
        return touristGuideRepository.findById(id);
    }

    public TouristGuide createTouristGuide(TouristGuide touristGuide) {
        return touristGuideRepository.save(touristGuide);
    }

    public TouristGuide updateTouristGuide(Long id, TouristGuide touristGuide) {
        touristGuide.setId(id);
        return touristGuideRepository.save(touristGuide);
    }

    public void deleteTouristGuide(Long id) {
        touristGuideRepository.deleteById(id);
    }

    public Optional<TouristGuide> getTouristGuideByUserId(Long userId) {
        return touristGuideRepository.findByUserId(userId);
    }

    public Optional<TouristGuide> getTouristGuideByEmail(String email) {
        return touristGuideRepository.findByEmail(email);
    }

    public Optional<TouristGuide> getTouristGuideByLicenseNumber(String licenseNumber) {
        return touristGuideRepository.findByLicenseNumber(licenseNumber);
    }

    public List<TouristGuide> getTouristGuidesByLocation(String location) {
        return touristGuideRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<TouristGuide> getTouristGuidesByLanguage(String language) {
        return touristGuideRepository.findByLanguageContainingIgnoreCase(language);
    }

    public List<TouristGuide> getTouristGuidesByAvailability(String availability) {
        return touristGuideRepository.findByAvailability(availability);
    }

    public List<TouristGuide> getTouristGuidesByMinimumExperience(Integer minExperience) {
        return touristGuideRepository.findByMinimumExperience(minExperience);
    }

    public List<TouristGuide> getTouristGuidesByCategory(String category) {
        return touristGuideRepository.findByCategory(category);
    }

    public List<TouristGuide> searchTouristGuidesByName(String name) {
        return touristGuideRepository.findByNameContainingIgnoreCase(name);
    }
}
