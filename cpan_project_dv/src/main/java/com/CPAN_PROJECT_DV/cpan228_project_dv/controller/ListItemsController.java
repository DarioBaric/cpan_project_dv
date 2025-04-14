package com.CPAN_A3_DV.cpan228_a3_dv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.CPAN_A3_DV.cpan228_a3_dv.data.ItemRepository;

@Controller
public class ListItemsController {

    @Autowired
    private ItemRepository itemRepo;

    @GetMapping("/listitems")
    public String showListItems(Model model) {
        model.addAttribute("items", itemRepo.findAll());
        return "listitems"; 
    }
}