package se.tega.biportalen.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tega.biportalen.model.Links;
import se.tega.biportalen.repository.LinksRepository;

import java.util.List;

@Service
public class LinksService {
    @Autowired
    LinksRepository linksRepository;
    // Replace with repository logic
    public List<Links> findAll() {

        return linksRepository.findAll();
    }
    public Links findById(Long id) {
        return (Links) linksRepository.findById(id);
    }
    public void save(Links link) {
        linksRepository.save(link);
    }

    @Transactional
    public void deleteById(Long id) {
        linksRepository.deleteById(id);
    }

    public List<Links> getLinks(Long categoryId) {
        return linksRepository.findByLinkCategoryId(categoryId);
    }

    public List<Links> getLinksByCountryAndCategory(Long countryId, Long categoryId) {
        return linksRepository.findByCountryIdAndLinkCategoryId(countryId, categoryId);
    }
}


