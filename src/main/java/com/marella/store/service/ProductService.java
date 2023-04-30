package com.marella.store.service;

import com.marella.store.model.Category;
import com.marella.store.model.Product;
import com.marella.store.model.Supplier;
import com.marella.store.repo.CategoryRepository;
import com.marella.store.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product>getAllProduct(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        Double hpp = product.getHpp();
        Double stock = product.getStock().doubleValue();
        Double total = hpp * stock;
        product.setId(product.getId());
        product.setKode_brg(product.getKode_brg());
        product.setName(product.getName());
        product.setCategory(product.getCategory());
        product.setSuppliers(product.getSuppliers());
        product.setHpp(product.getHpp());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());
        product.setTotal(total);
        productRepository.save(product);
    }

    public Product getById(Long id){
        return productRepository.getReferenceById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getByKeyword(String keyword){
        return productRepository.findByKeyword(keyword);
    }

    public Product getByCode(String kode_brg){
        return productRepository.findByCode(kode_brg);
    }

    public Double sumTotal(Double subtotal){
        return productRepository.totalAsset(subtotal);
    }

}
