package com.marella.store.service;

import com.marella.store.model.Buy;
import com.marella.store.model.Sell;
import com.marella.store.repo.BuyRepository;
import com.marella.store.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@Transactional
public class BuyService {
    @Autowired
    BuyRepository buyRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Buy> getAllBuy(){
        return buyRepository.findAll();

    }
    public Buy getById(Long id){
        return buyRepository.getReferenceById(id);
    }

    public void addBuy(Buy buy){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Double quantity = buy.getQuantity().doubleValue();

        Double hpp = buy.getProducts().iterator().next().getHpp();
        Long id = buy.getProducts().iterator().next().getId();
        Integer old_stock = buy.getProducts().iterator().next().getStock();
        Integer in = buy.getQuantity();
        Integer stock = old_stock + in;

        Double subtotal = quantity * hpp;
        Double total = stock * hpp;
        buy.setId(buy.getId());
        buy.setProducts(buy.getProducts());
        buy.setDate(dtf.format(now));
        buy.setQuantity(buy.getQuantity());
        buy.setSubtotal(subtotal);
        productRepository.updateStock(id,stock,total);
        buyRepository.save(buy);
    }

    public void deleteById(Long id){
        Buy buy = buyRepository.getReferenceById(id);
        Long proId = buy.getProducts().iterator().next().getId();
        Integer old_stock = buy.getProducts().iterator().next().getStock();
        Double hpp = buy.getProducts().iterator().next().getHpp();
        Integer sel = buy.getQuantity();
        Integer stock = old_stock - sel;
        Double total = stock * hpp;
        productRepository.updateStock(proId,stock,total);
        buyRepository.deleteById(id);
    }
    public Double sumTotal(Double subtotal){
        return buyRepository.totalBuy(subtotal);
    }

}
