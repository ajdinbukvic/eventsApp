package com.ptf.si.wp.zadaca1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.models.out.CategoryOut;
import com.ptf.si.wp.zadaca1.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService _categoryService;

  @GetMapping(value = "/")
  public List<CategoryOut> getAllCategories() {
    return _categoryService.getAllCategories();
  }

  @PostMapping(value = "/save")
  public CategoryOut addNewCategory(@RequestBody CategoryIn categoryIn) {
    return _categoryService.addNewCategory(categoryIn);
  }

  @PutMapping(value = "/update/{id}")
  public CategoryOut updateCategory(@PathVariable("id") Long id, @RequestBody CategoryIn categoryIn) {
    return _categoryService.updateCategory(id, categoryIn);
  }
}
