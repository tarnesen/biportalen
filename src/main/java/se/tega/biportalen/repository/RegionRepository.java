package se.tega.biportalen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.tega.biportalen.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

}
