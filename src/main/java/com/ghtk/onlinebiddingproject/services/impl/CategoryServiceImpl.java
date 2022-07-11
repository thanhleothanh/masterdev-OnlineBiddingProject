package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.models.entities.Category;
import com.ghtk.onlinebiddingproject.repositories.CategoryRepository;
import com.ghtk.onlinebiddingproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> get() {
        return categoryRepository.findAll();
    }
}
