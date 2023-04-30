package com.marella.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/buy-report/")
public class ReportBuyController {

    @GetMapping(value = "")
    public String showReport(Model model){
        String page = "Masih dalam proses develop";
        model.addAttribute("page",page);
        return "/buy-report/index";
    }
}
