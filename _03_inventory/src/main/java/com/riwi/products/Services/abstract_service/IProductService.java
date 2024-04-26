package com.riwi.products.Services.abstract_service;

import java.util.List;

import com.riwi.products.Entity.Product;

public interface IProductService {

    public Product save(Product objProduct);

    public List<Product> getAll();

    public Product getById(Long id);

    public void delete(Long id);

    public Product update(Product objProduct);
}

