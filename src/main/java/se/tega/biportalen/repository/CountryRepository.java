package se.tega.biportalen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.tega.biportalen.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCode(String string);
}
