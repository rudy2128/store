package com.marella.store.controller;

import com.marella.store.model.*;
import com.marella.store.service.BuyService;
import com.marella.store.service.CategoryService;
import com.marella.store.service.ProductService;
import com.marella.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/buy/")
public class BuyController {

    @Autowired
    BuyService buyService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;
    @GetMapping("/")
    public String showFormAddProduct(Model model,Buy buy, Double subtotal) {
        Double grand = buyService.sumTotal(subtotal);
        model.addAttribute("grand", grand);

        Integer pay = 0;
        model.addAttribute("pay", pay);
        double cashback = grand - pay;
        model.addAttribute("cashback", cashback);
        List<Buy> buys = buyService.getAllBuy();
        model.addAttribute("buys", buys);
        List<Product> options = productService.getAllProduct();
        model.addAttribute("options", options);
        return "/buy/index";
    }


    @PostMapping(value = "/add-buy")
    public String addBuy(@Validated Buy buy, BindingResult result){
        if (result.hasErrors()){
            return "/buy/index";
        }else {
            buyService.addBuy(buy);
            return "redirect:/buy/";
        }

    }
    @GetMapping(value = "/edit-buy/{id}")
    public String showUpdateForm(@PathVariable("id")Long id,Model model){
        List<Category> options = categoryService.getAllCategory();
        model.addAttribute("options",options);
        List<Supplier>supply = supplierService.getAllSupplier();
        model.addAttribute("supply",supply);
        Buy buy = buyService.getById(id);
        model.addAttribute("buy",buy);
        return "/buy/update-buy";
    }

    @PostMapping(value = "/update-buy/{id}")
    public String updateProduct(@PathVariable("id")Long id,@Validated Buy buy, BindingResult result, Model model){
        if (result.hasErrors()){
            buy.setId(id);
            return "/buy/update-buy";
        }else {
            buyService.addBuy(buy);
            return "redirect:/buy";
        }

    }
    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        buyService.deleteById(id);
        return "redirect:/buy/";

    }
    @GetMapping(value = "/search/{id}")
    public String findByCode(Long id, Model model){
        if (id !=null){
            Product option = productService.getById(id);
            model.addAttribute("option",option);
            Buy buy = new Buy();
            model.addAttribute("buy",buy);
        }else {
            List<Product>products = productService.getAllProduct();
            model.addAttribute("products",products);
        }
        return "/buy/add-buy";

    }


}
