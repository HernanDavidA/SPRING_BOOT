package com.riwi.products.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.products.Entity.Product;
import com.riwi.products.Repository.ProductRepository;
import com.riwi.products.Services.abstract_service.IProductService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private final ProductRepository objProductRepository;
    @Override
    public Product save(Product objProduct) {
        return this.objProductRepository.save(objProduct);
    }

    @Override
    public List<Product> getAll() {
        return this.objProductRepository.findAll();
    }

    @Override
    public Product getById(Long id) {

      return this.objProductRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {

        Product product =  this.objProductRepository.findById(id).orElseThrow();
        this.objProductRepository.delete(product);
    }

    @Override
    public Product update(Product objProduct) {
       return this.objProductRepository.save(objProduct);
    }
    
}
