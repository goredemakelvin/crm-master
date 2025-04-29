package com.cicosy.crm.web;

import com.cicosy.crm.data.CityData;
import com.cicosy.crm.entity.City;
import com.cicosy.crm.service.CityService;
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
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city-form")
    public String city(Model model) {
        CityData customerData = new CityData();
        model.addAttribute("city", customerData);
        return "city-form.html";
    }

    @PostMapping("/save-city")
    public String saveCity(@Valid @ModelAttribute("city") CityData cityData,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "city-form.html";
        }
        City city = null;
        if (cityData.getId() == null) {
            city = new City();
        } else {
            Optional<City> optionalCity = cityService.findById(cityData.getId());
            city = optionalCity.get();
        }
        city.setName(cityData.getName());
        city.setId(cityData.getId());
        cityService.save(city);
        redirectAttributes.addFlashAttribute("successMessage", "City saved successfully!");
        redirectAttributes.addAttribute("cityId", city.getId());
        return "redirect:/city-list";
    }

    @GetMapping("/city-list")
    public String getPaginatedCities(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<City> cityPage = cityService.findAllCities(pageable);
        model.addAttribute("cityPage", cityPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", cityPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "city-list.html";
    }

    @GetMapping("/city/{id}")
    public String getCity(@PathVariable Long id, Model model) {

        Optional<City> optionalCity = cityService.findById(id);
        model.addAttribute("city", optionalCity.get());
        return "city-form.html";
    }


}
