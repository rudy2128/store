package com.marella.store.service;

import com.marella.store.model.Product;
import com.marella.store.model.Supplier;
import com.marella.store.repo.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }

    public void addSupplier(Supplier supplier){
        supplierRepository.save(supplier);
    }

    public Supplier getSupById(Long id){
        return supplierRepository.getReferenceById(id);
    }

    public void deleteSupById(Long id){
        supplierRepository.deleteById(id);
    }
}
