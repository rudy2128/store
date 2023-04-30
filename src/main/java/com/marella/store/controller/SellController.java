package com.marella.store.controller;

import com.marella.store.component.PdfGeneratorUtil;
import com.marella.store.model.Customer;
import com.marella.store.model.Product;
import com.marella.store.model.Sell;
import com.marella.store.service.CustomerService;
import com.marella.store.service.ProductService;
import com.marella.store.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("")
public class SellController {

    @Autowired
    ProductService productService;

    @Autowired
    SellService sellService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PdfGeneratorUtil pdfGeneratorUtil;

    @PostMapping("/add-sell")
    public String addProduct(@Validated Sell sell, BindingResult result){
        if(result.hasErrors()){
            return "/index";
        }
        sellService.addSell(sell);
        return "redirect:/";

    }

    @GetMapping("")
    public String showFormAddProduct(Model model, Double subtotal){
        Double grand = sellService.sumTotal(subtotal);
        model.addAttribute("grand",grand);
        Sell sell = new Sell();
        model.addAttribute("sell",sell);
        List<Sell>sells = sellService.getAllSell();
        model.addAttribute("sells",sells);
        List<Product> options = productService.getAllProduct();
        model.addAttribute("options",options);
        List<Customer>cusOption = customerService.findAllCustomer();
        model.addAttribute("cusOption",cusOption);
        return "index";
    }


    @GetMapping("/delete/{id}")
    public String deleteSell(@PathVariable("id") long id) {
        sellService.deleteById(id);
        return "redirect:/";
    }





    @GetMapping(value = "/search/{id}")
    public String findByCode(Long id,Model model) {
        if (id !=null){
            Product option = productService.getById(id);
            model.addAttribute("option",option);
            Sell sell = new Sell();
            model.addAttribute("sell",sell);
            List<Customer>cusOption = customerService.findAllCustomer();
            model.addAttribute("cusOption",cusOption);
            return "/add-sell";
        }else {
            List<Product>products = productService.getAllProduct();
            model.addAttribute("products",products);
        }
            return "/index";
    }


    @GetMapping(value = "/print/{id}")
    public String printReport(Long id){
        Sell sell = sellService.getById(id);
        Map<String, Object> data = new HashMap<String,Object>();
        data.put("sellList", sell);
        try {
            pdfGeneratorUtil.createPdf("laporan",data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }



}
