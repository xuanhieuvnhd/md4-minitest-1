package com.service.category;

import com.model.Category;
import com.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    List<Category> findAll();
}
