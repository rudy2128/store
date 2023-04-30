package com.marella.store.controller;

import com.marella.store.model.Category;
import com.marella.store.model.Product;
import com.marella.store.model.Supplier;
import com.marella.store.service.CategoryService;
import com.marella.store.service.ProductService;
import com.marella.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SupplierService supplierServices;

    @GetMapping("/input")
    public String showFormAddProduct(Product product,Model model) {
        List<Category> options = categoryService.getAllCategory();
        model.addAttribute("options",options);
        List<Supplier>supply = supplierServices.getAllSupplier();
        model.addAttribute("supply",supply);
        return "product/add-product";
    }

    @GetMapping("/")
    public String showProductList(Model model,Double subtotal) {
        Double grand = productService.sumTotal(subtotal);
        model.addAttribute("grand",grand);
        model.addAttribute("products", productService.getAllProduct());
        return "product/index";
    }

    @PostMapping(value = "/add-product")
    public String addProduct(@Valid  Product product, BindingResult result){
       if(result.hasErrors()){
            return "/product/add-product";
        }
        productService.addProduct(product);
        return "redirect:/product/";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        List<Category> options = categoryService.getAllCategory();
        model.addAttribute("options",options);
        List<Supplier>supply = supplierServices.getAllSupplier();
        model.addAttribute("supply",supply);
        Product product = productService.getById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("product", product);
        return "/product/update-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Validated Product product,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "/product/update-product";
        }

        productService.addProduct(product);
        return "redirect:/product/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        productService.deleteById(id);
        return "redirect:/product/";
    }

    @GetMapping("/findByKeyword/{keyword}")
    public String findByName(Product product,Model model,String keyword){
        if (keyword!=null){
            List<Product> products = productService.getByKeyword(keyword);
            model.addAttribute("products",products);
        }else {
            List<Product>products = productService.getAllProduct();
            model.addAttribute("products",products);
        }
        return "product/index";

    }

}
