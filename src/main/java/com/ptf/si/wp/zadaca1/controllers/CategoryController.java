package com.ptf.si.wp.zadaca1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.models.out.CategoryOut;
import com.ptf.si.wp.zadaca1.services.CategoryService;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService _categoryService;

  @GetMapping(value = "/")
  public List<CategoryOut> getAllCategories() {
    return _categoryService.getAllCategories();
  }

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
