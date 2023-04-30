package com.marella.store.controller;

import com.marella.store.model.Category;
import com.marella.store.model.Product;
import com.marella.store.model.User;
import com.marella.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String showCategoryList(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "/category/index";
    }
    @GetMapping("/input-category")
    public String showCategoryForm(Category category) {
        return "category/add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@Validated Category category, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/category/add-category";
        }
        categoryService.addCategory(category);
        return "redirect:/category/";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("category", category);
        return "/category/update-category";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Validated Category category,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "/category/update-category";
        }

        categoryService.addCategory(category);
        return "redirect:/category/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category/";
    }

}
