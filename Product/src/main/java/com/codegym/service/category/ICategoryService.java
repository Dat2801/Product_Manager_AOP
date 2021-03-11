package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.service.IService;

public interface ICategoryService extends IService<Category>{
    Category findByName(String name);
}
