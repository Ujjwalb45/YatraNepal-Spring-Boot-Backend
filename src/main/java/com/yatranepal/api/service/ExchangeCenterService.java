package com.yatranepal.api.service;

import com.yatranepal.api.model.ExchangeCenter;
import com.yatranepal.api.repository.ExchangeCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeCenterService {

    @Autowired
    private ExchangeCenterRepository exchangeCenterRepository;

    public List<ExchangeCenter> getAllExchangeCenters() {
        return exchangeCenterRepository.findAll();
    }

    public Optional<ExchangeCenter> getExchangeCenterById(Long id) {
        return exchangeCenterRepository.findById(id);
    }

    public ExchangeCenter createExchangeCenter(ExchangeCenter exchangeCenter) {
        return exchangeCenterRepository.save(exchangeCenter);
    }

    public ExchangeCenter updateExchangeCenter(Long id, ExchangeCenter exchangeCenter) {
        exchangeCenter.setId(id);
        return exchangeCenterRepository.save(exchangeCenter);
    }

    public void deleteExchangeCenter(Long id) {
        exchangeCenterRepository.deleteById(id);
    }

    public List<ExchangeCenter> getExchangeCentersByOwnerId(Long ownerId) {
        return exchangeCenterRepository.findByOwnerId(ownerId);
    }

    public List<ExchangeCenter> getActiveExchangeCenters() {
        return exchangeCenterRepository.findActiveExchangeCenters();
    }

    public List<ExchangeCenter> getExchangeCentersByStatus(Boolean isActive) {
        return exchangeCenterRepository.findByIsActive(isActive);
    }

    public List<ExchangeCenter> searchExchangeCentersByName(String name) {
        return exchangeCenterRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ExchangeCenter> searchExchangeCentersByAddress(String address) {
        return exchangeCenterRepository.findByAddressContainingIgnoreCase(address);
    }

    public List<ExchangeCenter> searchExchangeCentersByService(String service) {
        return exchangeCenterRepository.findByServicesContainingIgnoreCase(service);
    }

    public List<ExchangeCenter> getExchangeCentersNearLocation(BigDecimal lat, BigDecimal lng, Double radiusKm) {
        return exchangeCenterRepository.findByLocationWithinRadius(lat, lng, radiusKm);
    }

    public ExchangeCenter activateExchangeCenter(Long id) {
        Optional<ExchangeCenter> exchangeCenterOpt = exchangeCenterRepository.findById(id);
        if (exchangeCenterOpt.isPresent()) {
            ExchangeCenter exchangeCenter = exchangeCenterOpt.get();
            exchangeCenter.setIsActive(true);
            return exchangeCenterRepository.save(exchangeCenter);
        }
        return null;
    }

    public ExchangeCenter deactivateExchangeCenter(Long id) {
        Optional<ExchangeCenter> exchangeCenterOpt = exchangeCenterRepository.findById(id);
        if (exchangeCenterOpt.isPresent()) {
            ExchangeCenter exchangeCenter = exchangeCenterOpt.get();
            exchangeCenter.setIsActive(false);
            return exchangeCenterRepository.save(exchangeCenter);
        }
        return null;
    }
}
