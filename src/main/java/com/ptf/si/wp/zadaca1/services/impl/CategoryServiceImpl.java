package com.ptf.si.wp.zadaca1.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.Category;
import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.models.out.CategoryOut;
import com.ptf.si.wp.zadaca1.repositories.CategoryRepository;
import com.ptf.si.wp.zadaca1.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository _categoryRepository;

  @Override
  public List<CategoryOut> getAllCategories() {
    List<Category> categories = _categoryRepository.findAll();
    List<CategoryOut> categoryOuts = new ArrayList<CategoryOut>();
    categories.forEach(c -> categoryOuts.add(new CategoryOut(c)));
    return categoryOuts;
  }

  @Override
  public void addNewCategory(CategoryIn categoryIn) {
    Category c;
    try {
      c = new Category(categoryIn);
      _categoryRepository.save(c);
    } catch (Exception e) {

    }
  }

  @Override
  public void updateCategory(CategoryIn categoryIn) {
    Category c = _categoryRepository.findById(categoryIn.getId()).get();
    try {
      if (c != null) {
        Category updatedCategory = new Category(categoryIn);
        _categoryRepository.save(updatedCategory);
      } else
        throw new IllegalArgumentException("Kategorija s tim ID-om ne postoji!");
    } catch (Exception e) {

    }
  }

}
