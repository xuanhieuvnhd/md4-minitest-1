package com.repository.category;

import com.model.Category;
import com.repository.IGeneralRepository;

import java.util.List;

public interface ICategoryRepository extends IGeneralRepository<Category> {
    List<Category> findAll();
}
