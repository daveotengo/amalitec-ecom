package com.amalitec.amalitececom.service;


import com.amalitec.amalitececom.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(UUID id, Product product);
    boolean deleteProduct(UUID id);

    boolean existsById(UUID id);

    List<Product> createProductList(List<Product> product1);

    Product getByProductName(String productA);

    List<Product> getLowStockItems(int threshold);
}

