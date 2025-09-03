package com.yatranepal.api.service;

import com.yatranepal.api.model.ChadParba;
import com.yatranepal.api.repository.ChadParbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChadParbaService {

    @Autowired
    private ChadParbaRepository chadParbaRepository;

    public List<ChadParba> getAllChadParba() {
        return chadParbaRepository.findAllOrderByDate();
    }

    public Optional<ChadParba> getChadParbaById(Long id) {
        return chadParbaRepository.findById(id);
    }

    public ChadParba createChadParba(ChadParba chadParba) {
        return chadParbaRepository.save(chadParba);
    }

    public ChadParba updateChadParba(Long id, ChadParba chadParba) {
        chadParba.setId(id);
        return chadParbaRepository.save(chadParba);
    }

    public void deleteChadParba(Long id) {
        chadParbaRepository.deleteById(id);
    }

    public List<ChadParba> getChadParbaByMonth(ChadParba.NepaliMonth nepaliMonth) {
        return chadParbaRepository.findByNepaliMonth(nepaliMonth);
    }

    public List<ChadParba> getChadParbaByCategory(String category) {
        return chadParbaRepository.findByCategoryContainingIgnoreCase(category);
    }

    public List<ChadParba> searchChadParbaByTitle(String title) {
        return chadParbaRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<ChadParba> getChadParbaByDate(ChadParba.NepaliMonth month, Integer day) {
        return chadParbaRepository.findByNepaliMonthAndDay(month, day);
    }
}
