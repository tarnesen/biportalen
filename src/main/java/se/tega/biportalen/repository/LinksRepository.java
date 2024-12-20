package se.tega.biportalen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.tega.biportalen.model.Links;

import javax.swing.text.html.Option;
import java.util.List;

public interface LinksRepository extends JpaRepository<Links, Integer> {
@Query("SELECT l FROM Links l WHERE l.categoryId = ?1")
    List<Links> findByLinkCategoryId(Long categoryId);

    void deleteById(Long id);

    Links findById(Long id);
@Query("SELECT l FROM Links l WHERE l.countryId = ?1 AND l.categoryId = ?2")
    List<Links> findByCountryIdAndLinkCategoryId(Long countryId, Long categoryId);
}
