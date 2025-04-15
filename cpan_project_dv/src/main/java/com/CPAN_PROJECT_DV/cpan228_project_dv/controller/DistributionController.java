package com.CPAN_PROJECT_DV.cpan228_project_dv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.CPAN_PROJECT_DV.cpan228_project_dv.data.DistributionRepository;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Distribution;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Item;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/distributioncentres")
public class DistributionController {

    @Autowired
    private DistributionRepository centreRepo;

    @ModelAttribute("distributionCentre")
    public Distribution distributionCentre() {
        return new Distribution(null, "Default Centre", 0.0, 0.0, null);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String showDistributionCentres(Model model) {
        log.info("Fetching all distribution centres");
        List<Distribution> centres = centreRepo.findAll();
        model.addAttribute("centres", centres);
        return "distributioncentres"; // Points to the distributioncentres.html template
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasRole('ADMIN')")
    public String listItemsInCentre(@PathVariable Long id, Model model) {
        Distribution centre = centreRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid centre ID"));
        model.addAttribute("items", centre.getItems());
        model.addAttribute("centreName", centre.getName());
        log.info("Displaying items for distribution centre ID: {}", id);
        return "listitems";
    }

    @PostMapping("/{id}/additem")
    @PreAuthorize("hasRole('ADMIN')")
    public String addItemToCentre(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) {
        Distribution centre = centreRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid centre ID"));
        item.setDistribution(centre);
        centre.getItems().add(item);
        centreRepo.save(centre);
        log.info("Added item to distribution centre ID: {}", id);
        return "redirect:/distributioncentres";
    }

    @PostMapping("/{id}/deleteitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteItemFromCentre(@PathVariable Long id, @PathVariable Long itemId) {
        Distribution centre = centreRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid centre ID"));
        centre.getItems().removeIf(item -> item.getId().equals(itemId));
        centreRepo.save(centre);
        log.info("Deleted item ID: {} from distribution centre ID: {}", itemId, id);
        return "redirect:/distributioncentres";
    }
}
