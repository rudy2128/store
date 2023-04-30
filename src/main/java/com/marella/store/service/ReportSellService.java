package com.marella.store.service;

import com.marella.store.model.Sell;
import com.marella.store.repo.SellRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ReportSellService {

    @Autowired
    SellRepository sellRepository;

    public List<Sell>findAllToday(){
        // Convert String to LocalDateTime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        return sellRepository.findAllToday(date);
    }

    public List<Sell>findBetweenDate(String startDate,String endDate){
        return sellRepository.getAllBetweenDates(startDate,endDate);
    }
}
