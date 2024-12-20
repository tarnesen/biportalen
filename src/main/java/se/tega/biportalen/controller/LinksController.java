package se.tega.biportalen.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.tega.biportalen.model.Country;
import se.tega.biportalen.model.LinkCatagory;
import se.tega.biportalen.model.Links;
import se.tega.biportalen.service.CountryService;
import se.tega.biportalen.service.LinkCategoryService;
import se.tega.biportalen.service.LinksService;
import se.tega.biportalen.service.RegionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping
public class LinksController {

    private final LinksService linksService;
    private final CountryService countryService;
    private final LinkCategoryService categoryService;
    private final RegionService regionService;

    public LinksController(LinksService linksService, CountryService countryService, LinkCategoryService categoryService, RegionService regionService) {
        this.linksService = linksService;
        this.countryService = countryService;
        this.categoryService = categoryService;
        this.regionService = regionService;
    }

    @GetMapping("/admin/links/deleteCategory")
    public String deleteCategory(Model model,Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user has the ADMIN role
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);

        model.addAttribute("links", linksService.findAll());
        for (int i = 0; i <  countryService.findAll().size();i++) {
            System.out.println(countryService.findAll().get(i).getName());
            System.out.println(countryService.findAll().get(i).getId());
        }

        if (isAdmin) {
            categoryService.deleteById(id);
        }
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("linkForm", new Links());
        if (isAdmin) {
            model.addAttribute("content", "admin/links/links");
            model.addAttribute("submenu", "admin/links/submenu");
            return "admin/main";
        } else {
            model.addAttribute("content", "links/links");
            model.addAttribute("submenu", "links/submenu");
            return "main";
        }
    }

    @PostMapping("/admin/links/addCategory")
    public String addCategory(Model model,String categoryName,String categoryLang) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user has the ADMIN role
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);

        model.addAttribute("links", linksService.findAll());
        for (int i = 0; i <  countryService.findAll().size();i++) {
            System.out.println(countryService.findAll().get(i).getName());
            System.out.println(countryService.findAll().get(i).getId());
        }

        if (isAdmin) {
            LinkCatagory linkCatagory = new LinkCatagory();
            linkCatagory.setName(categoryName);
            linkCatagory.setLang(categoryLang);
            categoryService.save(linkCatagory);
        }
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("linkForm", new Links());
        if (isAdmin) {
            model.addAttribute("content", "admin/links/links");
            model.addAttribute("submenu", "admin/links/submenu");
            return "admin/main";
        } else {
            model.addAttribute("content", "links/links");
            model.addAttribute("submenu", "links/submenu");
            return "main";
        }
    }

    @GetMapping("/user/links")
    public String listLinks(Model model) {
        // Get the current authentication object
        Locale currentLocale = LocaleContextHolder.getLocale();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user has the ADMIN role
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);

        model.addAttribute("links", linksService.findAll());
        for (int i = 0; i <  countryService.findAll().size();i++) {
            System.out.println(countryService.findAll().get(i).getName());
            System.out.println(countryService.findAll().get(i).getId());
        }

        System.out.println(countryService.findAll());
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("linkForm", new Links());
        if (isAdmin) {
            model.addAttribute("categories", categoryService.findAll());

            model.addAttribute("content", "admin/links/links");
            model.addAttribute("submenu", "admin/links/submenu");
            return "admin/main";
        } else {
            List<Country> countries = countryService.findAll();
            // Check that currentLocale.toString() is one of the countries if not set to the first country
            boolean found = false;
            for (int i=0;i<countries.size();i++) {
                if(countries.get(i).getCode().equals(currentLocale.toString())) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                model.addAttribute("categories", categoryService.findAllByLang(countries.get(0).getCode()));
            }else{
                model.addAttribute("categories", categoryService.findAllByLang(currentLocale.toString()));
            }


            model.addAttribute("content", "links/links");
            model.addAttribute("submenu", "links/submenu");
            return "main";
        }


    }

    @PostMapping("/admin/links/addLink")
    @ResponseBody
    public ResponseEntity<String> addLink(
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam Long countryId,
            @RequestParam Long categoryId,
            @RequestParam Long regionId) {

        Links link = new Links();
        link.setTitle(title);
        link.setUrl(url);
        link.setDescription(description);
        link.setCountryId(countryId);
        link.setCategoryId(categoryId);
        link.setRegionId(regionId);

        // Validering (valfritt)
        if (link.getTitle() == null || link.getUrl() == null) {
            return ResponseEntity.badRequest().body("Titel och URL är obligatoriska.");
        }

        // Spara länken
        linksService.save(link);
        return ResponseEntity.ok("Länken har sparats.");
    }

    @GetMapping("/user/links/getLinks")
    @ResponseBody
    public List<Links> getLinks(Long categoryId) {
        // Get locale from the request
        Locale currentLocale = LocaleContextHolder.getLocale();
        Long countryId;

        List<Country> countries = countryService.findAll();
        // Check that currentLocale.toString() is one of the countries if not set to the first country
        boolean found = false;
        for (int i=0;i<countries.size();i++) {
            if(countries.get(i).getCode().equals(currentLocale.toString())) {
                found = true;
                break;
            }
        }
        if(!found) {
            countryId=countryService.findByCode(countries.get(0).getCode());
        }else{
            countryId=countryService.findByCode(currentLocale.toString());
        }

        List<Links> links = linksService.getLinksByCountryAndCategory(countryId,categoryId);
        return links;
    }

    @PostMapping("/admin/links/deleteLink")
    @ResponseBody
    public String deleteLink(@RequestParam Long id) {
        linksService.deleteById(id);
        return "Länken har raderats.";
    }

}
