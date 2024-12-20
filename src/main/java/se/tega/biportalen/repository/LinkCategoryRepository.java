package se.tega.biportalen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.tega.biportalen.model.LinkCatagory;

import java.util.List;

public interface LinkCategoryRepository extends JpaRepository<LinkCatagory, Integer> {
    void deleteById(Long id);

@Query("SELECT l FROM LinkCatagory l WHERE l.lang = ?1")
    List<LinkCatagory> findAllByLang(String string);
}
