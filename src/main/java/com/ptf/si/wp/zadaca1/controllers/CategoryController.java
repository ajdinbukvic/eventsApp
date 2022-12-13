package com.ptf.si.wp.zadaca1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService _categoryService;

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addNewCategory(@ModelAttribute("categoryIn") CategoryIn categoryIn, Model model) {
    _categoryService.addNewCategory(categoryIn);
    model.addAttribute("success", "Uspješno ste dodali novu kategoriju!");
    model.addAttribute("categories", _categoryService.getAllCategories());
    return "category-manage";
  }

  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateCategory(@ModelAttribute("categoryIn") CategoryIn categoryIn, Model model) {
    _categoryService.updateCategory(categoryIn);
    model.addAttribute("success", "Uspješno ste uredili kategoriju (ID: " + categoryIn.getId() + ")");
    model.addAttribute("categories", _categoryService.getAllCategories());
    return "category-manage";
  }
}
