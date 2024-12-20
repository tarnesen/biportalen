package se.tega.biportalen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tega.biportalen.model.Country;
import se.tega.biportalen.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Long findByCode(String string) {
        return countryRepository.findByCode(string).getId();
    }
}
