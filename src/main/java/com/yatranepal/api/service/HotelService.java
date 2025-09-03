package com.yatranepal.api.service;

import com.yatranepal.api.model.Hotel;
import com.yatranepal.api.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel hotel) {
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> getFeaturedHotels() {
        return hotelRepository.findByFeatured(true);
    }

    public List<Hotel> getHotelsByType(String type) {
        return hotelRepository.findByType(type);
    }

    public List<Hotel> getHotelsByPriceRange(Double minPrice, Double maxPrice) {
        return hotelRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Hotel> searchHotelsByName(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Hotel> getHotelsByCityAndType(String city, String type) {
        return hotelRepository.findByCityAndType(city, type);
    }
}
