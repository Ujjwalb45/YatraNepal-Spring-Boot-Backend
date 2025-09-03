package com.yatranepal.api.service;

import com.yatranepal.api.model.Place;
import com.yatranepal.api.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Place updatePlace(Long id, Place place) {
        place.setId(id);
        return placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    public List<Place> getPlacesByCity(String city) {
        return placeRepository.findByCity(city);
    }

    public List<Place> getPlacesByCategory(String category) {
        return placeRepository.findByCategory(category);
    }

    public List<Place> searchPlacesByName(String name) {
        return placeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Place> getPlacesByCityAndCategory(String city, String category) {
        return placeRepository.findByCityAndCategory(city, category);
    }

    public List<Place> getPlacesNearLocation(Double longitude, Double latitude, Double maxDistance) {
        return placeRepository.findNearLocation(longitude, latitude, maxDistance);
    }
}
