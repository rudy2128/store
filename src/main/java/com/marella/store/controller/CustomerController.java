package com.marella.store.controller;

import com.marella.store.model.Customer;
import com.marella.store.model.Product;
import com.marella.store.model.Supplier;
import com.marella.store.service.CustomerService;
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
@RequestMapping(value = "/customer/")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping(value = "/")
    public String findAllCustomer(Model model){
        List<Customer>customerList = customerService.findAllCustomer();
        model.addAttribute("customerList",customerList);
        return "/customer/index";
    }
    @GetMapping(value = "/findById/{id}")
    public String findById(Long id, Model model){
        customerService.findById(id);
        return "redirect:/customer/";

    }
    @GetMapping("/input")
    public String showInputForm(Customer customer) {
        return "/customer/add-customer";
    }


    @PostMapping(value = "/add-customer")
    public String addCustomer(@Validated Customer customer, BindingResult result, Model model){
        if (result.hasErrors()){
            return "/customer/add-customer";
        }else {
            customerService.addCustomer(customer);
            return "redirect:/customer/";
        }

    }
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Validated Customer customer,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "/customer/update-supplier";
        }

        customerService.addCustomer(customer);
        return "redirect:/customer/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/update-customer";
    }
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteById(id);
        return "redirect:/customer/";
    }

    @GetMapping("/findByKeyword/{keyword}")
    public String findByName(Customer customer, Model model, String keyword){
        if (keyword!=null){
            List<Customer> customerList = customerService.findByKeyword(keyword);
            model.addAttribute("customerList",customerList);
        }else {
            List<Customer>customerList = customerService.findAllCustomer();
            model.addAttribute("customerList",customerList);

        }
        return "customer/index";

    }
}
