package com.marella.store.controller;

import com.marella.store.model.DateSearchDto;
import com.marella.store.model.Sell;
import com.marella.store.service.ReportSellService;
import com.marella.store.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping(value = "/report/")
public class ReportController {
    @Autowired
    ReportSellService reportSellService;
    @Autowired
    SellService sellService;
    @GetMapping(value = "/")
    public String showReportToday(Model model,Double subtotal) throws Exception{
        List<Sell>sells = reportSellService.findAllToday();
        model.addAttribute("sells",sells);
        System.out.println(sells);
        return "/report/index";
    }

    @GetMapping(value = "/between")
    public String betweenDate(DateSearchDto dateSearchDto,Model model){
        LocalDateTime start = LocalDateTime.of(LocalDate.from(dateSearchDto.getStartDate()), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.from(dateSearchDto.getEndDate()), LocalTime.of(23, 59, 59));

        List<Sell> sellList = reportSellService.findBetweenDate(start.toString(), end.toString());
        model.addAttribute("sellList",sellList);
        return "/report/index";




    }
}
