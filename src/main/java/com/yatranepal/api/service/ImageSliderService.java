package com.yatranepal.api.service;

import com.yatranepal.api.model.ImageSlider;
import com.yatranepal.api.repository.ImageSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageSliderService {

    @Autowired
    private ImageSliderRepository imageSliderRepository;

    public List<ImageSlider> getAllImageSliders() {
        return imageSliderRepository.findAllOrderByCreatedAtDesc();
    }

    public Optional<ImageSlider> getImageSliderById(Long id) {
        return imageSliderRepository.findById(id);
    }

    public ImageSlider createImageSlider(ImageSlider imageSlider) {
        return imageSliderRepository.save(imageSlider);
    }

    public ImageSlider updateImageSlider(Long id, ImageSlider imageSlider) {
        imageSlider.setId(id);
        return imageSliderRepository.save(imageSlider);
    }

    public void deleteImageSlider(Long id) {
        imageSliderRepository.deleteById(id);
    }

    public List<ImageSlider> searchImageSlidersByName(String name) {
        return imageSliderRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ImageSlider> getImageSlidersByType(ImageSlider.ImageType imageType) {
        return imageSliderRepository.findByImageType(imageType);
    }
}
