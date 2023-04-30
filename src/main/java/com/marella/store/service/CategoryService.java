package com.marella.store.service;

import com.marella.store.model.Category;
import com.marella.store.model.Product;
import com.marella.store.model.User;
import com.marella.store.repo.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public Category getById(Long id){
        return categoryRepository.getReferenceById(id);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }



}
