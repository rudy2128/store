package com.marella.store.controller;

import com.marella.store.model.Supplier;
import com.marella.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/input-supplier")
    public String showFormSup(Supplier supplier) {
        return "supplier/input-supplier";
    }

    @GetMapping("/")
    public String showSupplierList(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSupplier());
        return "supplier/index";
    }
    @PostMapping("/add-supplier")
    public String addSupplier(@Validated Supplier supplier, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/supplier/input-supplier";
        }
        supplierService.addSupplier(supplier);
        return "redirect:/supplier/";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.getSupById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("supplier", supplier);
        return "/supplier/update-supplier";
    }

    @PostMapping("/update/{id}")
    public String updateSupplier(@PathVariable("id") long id, @Validated Supplier supplier,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            supplier.setId(id);
            return "/supplier/update-supplier";
        }

        supplierService.addSupplier(supplier);
        return "redirect:/supplier/";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") long id) {
        supplierService.deleteSupById(id);
        return "redirect:/supplier/";
    }

}
