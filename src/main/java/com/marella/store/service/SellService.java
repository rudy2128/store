package com.marella.store.service;

import com.marella.store.model.Sell;
import com.marella.store.repo.ProductRepository;
import com.marella.store.repo.SellRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class SellService {
    @Autowired
    SellRepository sellRepository;

    @Autowired
    ProductRepository productRepository;

    public void addSell(Sell sell){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Double quantity = sell.getQuantity().doubleValue();
        Double price = sell.getProducts().listIterator().next().getPrice();
        Double hpp = sell.getProducts().listIterator().next().getHpp();
        Long id = sell.getProducts().listIterator().next().getId();
        Integer old_stock = sell.getProducts().listIterator().next().getStock();
        Integer sel = sell.getQuantity();
        Integer stock = old_stock - sel;

        Double subtotal = quantity * price;
        Double total = stock * hpp;
        sell.setId(sell.getId());
        sell.setProducts(sell.getProducts());
        sell.setCustomers(sell.getCustomers());
        sell.setDate(dtf.format(now));
        sell.setQuantity(sell.getQuantity());
        sell.setSubtotal(subtotal);
        productRepository.updateStock(id,stock,total);
        sellRepository.save(sell);


    }


    public List<Sell>getAllSell(){
        return sellRepository.findAll();
    }

    public void deleteById(Long id){
        Sell sell = sellRepository.getReferenceById(id);
        Long proId = sell.getProducts().listIterator().next().getId();
        Integer old_stock = sell.getProducts().listIterator().next().getStock();
        Double price = sell.getProducts().listIterator().next().getPrice();
        Integer sel = sell.getQuantity();
        Integer stock = old_stock + sel;
        Double total = stock * price;

        productRepository.updateStock(proId,stock,total);
        sellRepository.deleteById(id);
    }
    public Double sumTotal(Double subtotal){
        return sellRepository.totalSell(subtotal);
    }

    public Sell getById(Long id){
       return sellRepository.getReferenceById(id);

    }

    public List<Sell>sellToday(String date){
        return sellRepository.findAllToday(date);
    }





}
