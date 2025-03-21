package com.cicosy.crm.web;

import com.cicosy.crm.data.CountryData;
import com.cicosy.crm.entity.Country;
import com.cicosy.crm.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country-form")
    public String countryForm(Model model) {
        CountryData countryData = new CountryData();
        model.addAttribute("country", countryData);
        return "country-form.html";
    }

    @PostMapping("/save-country")
    public String saveCountry(@Valid @ModelAttribute("c") CountryData countryData,
                              BindingResult result, Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "country-form.html";
        }
        Country country = null;
        if (countryData.getId() == null) {
            country = new Country();

        } else {
            Optional<Country> optionalCountry = countryService.findById(countryData.getId());
            country = optionalCountry.get();
        }
        country.setName(countryData.getName());
        country.setCode(countryData.getCode());
        countryService.save(country);
        redirectAttributes.addAttribute("countryId", country.getId());
        return "redirect:/country-list";
    }

    @GetMapping("/country-list")
    public String getPaginatedCountries(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Country> CountryPage = countryService.findAllCountries(pageable);
        model.addAttribute("countryPage", CountryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", CountryPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "country-list.html";
    }

    @GetMapping("/country/{id}")
    public String countryForm(@PathVariable Long id, Model model) {
        Optional<Country> optionalCountry = countryService.findById(id);
        model.addAttribute("country", optionalCountry.get());
        return "country-form.html";
    }
}
