package com.ptf.si.wp.zadaca1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.models.out.CategoryOut;

@Service
public interface CategoryService {

  public List<CategoryOut> getAllCategories();

  public CategoryOut addNewCategory(CategoryIn categoryIn);

  public CategoryOut updateCategory(Long id, CategoryIn categoryIn);
}
