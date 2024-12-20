package se.tega.biportalen.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tega.biportalen.model.LinkCatagory;
import se.tega.biportalen.repository.LinkCategoryRepository;

import java.util.List;

@Service
public class LinkCategoryService {
    @Autowired
    LinkCategoryRepository linkCategoryRepository;
    public List<LinkCatagory> findAll() {
        return linkCategoryRepository.findAll();
    }

    public void save(LinkCatagory linkCatagory) {
        linkCategoryRepository.save(linkCatagory);
    }

    @Transactional
    public void deleteById(Long id) {
        linkCategoryRepository.deleteById(id);
    }



    public List<LinkCatagory> findAllByLang(String string) {
        return linkCategoryRepository.findAllByLang(string);
    }
}
