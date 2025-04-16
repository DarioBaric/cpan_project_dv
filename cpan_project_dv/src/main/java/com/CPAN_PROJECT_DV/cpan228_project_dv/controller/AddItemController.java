package com.CPAN_PROJECT_DV.cpan228_project_dv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.CPAN_PROJECT_DV.cpan228_project_dv.data.ItemRepository;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Item;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Brand;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/additem")
public class AddItemController {

    @Autowired
    private ItemRepository itemRepo;

    @ModelAttribute("item")
    public Item item() {
        return new Item(null, "", Brand.BALENCIAGA, 2025, 0.0, 10);
    }

    @ModelAttribute
    public void addBrands(Model model) {
        model.addAttribute("brands", Brand.values());
        log.info("Added brands to model");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_EMPLOYEE')")
    public String showAddItemForm(Model model) {
        return "additem";
    }

    @GetMapping("/listitems")
    public String showListItems(Model model) {
        model.addAttribute("items", itemRepo.findAll());
        return "listitems";
    }

    @GetMapping("/sortedListItems")
    public String showSortedItemList(Model model) {
        model.addAttribute("items", itemRepo.findAllByOrderByBrandAsc());
        return "listitems";
    }

    @GetMapping("/findByBrandAndYear")
    public String findByBrandAndYear(@RequestParam("brand") Brand brand, @RequestParam("year") int year, Model model) {
        Page<Item> items = itemRepo.findByBrandAndYear(brand, null);
        model.addAttribute("items", items.getContent());
        return "listitems";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_EMPLOYEE')")
    public String processAddItem(@ModelAttribute("item") Item item, Model model) {
        log.info("Received item with brand: {}", item.getBrand());

        if (item.getBrand() == null) {
            model.addAttribute("errorMessage", "Brand is required.");
            return "additem";
        }
        Item savedItem = itemRepo.save(item);
        log.info("Saved item: {}", savedItem);
        return "redirect:/listitems"; 
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        itemRepo.deleteById(id);
        return "redirect:/listitems";
    }
}