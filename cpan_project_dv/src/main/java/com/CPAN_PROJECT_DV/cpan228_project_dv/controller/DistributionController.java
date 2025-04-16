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
@RequestMapping("/distributions")
public class DistributionController {

    @Autowired
    private DistributionRepository distributionRepo;

    @ModelAttribute("distribution")
    public Distribution distribution() {
        return new Distribution(null, "Default Distribution", 0.0, 0.0, null);
    }

    @GetMapping("/distributioncentres")
    @PreAuthorize("hasRole('ADMIN')")
    public String showDistributions(Model model) {
        log.info("Fetching all distributions");
        List<Distribution> distributions = distributionRepo.findAll();
        model.addAttribute("distributioncentres", distributions);
        return "distributioncentres";
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasRole('ADMIN')")
    public String listItemsInDistribution(@PathVariable Long id, Model model) {
        Distribution distribution = distributionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid distribution ID"));
        model.addAttribute("items", distribution.getItems());
        model.addAttribute("distributionName", distribution.getName());
        log.info("Displaying items for distribution ID: {}", id);
        return "listitems";
    }

    @PostMapping("/{id}/additem")
    @PreAuthorize("hasRole('ADMIN')")
    public String addItemToDistribution(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) {
        Distribution distribution = distributionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid distribution ID"));
        item.setDistribution(distribution);
        distribution.getItems().add(item);
        distributionRepo.save(distribution);
        log.info("Added item to distribution ID: {}", id);
        return "redirect:/distributions";
    }

    @PostMapping("/{id}/deleteitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteItemFromDistribution(@PathVariable Long id, @PathVariable Long itemId) {
        Distribution distribution = distributionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid distribution ID"));
        distribution.getItems().removeIf(item -> item.getId().equals(itemId));
        distributionRepo.save(distribution);
        log.info("Deleted item ID: {} from distribution ID: {}", itemId, id);
        return "redirect:/distributions";
    }
}

