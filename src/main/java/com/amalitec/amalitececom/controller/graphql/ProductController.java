package com.amalitec.amalitececom.controller.graphql;



import com.amalitec.amalitececom.exception.ProductNotFoundException;
import com.amalitec.amalitececom.request_response.graphql.input.CreateProductInput;
import com.amalitec.amalitececom.request_response.graphql.input.UpdateProductInput;
import com.amalitec.amalitececom.request_response.graphql.output.DeletionStatus;
import com.amalitec.amalitececom.model.Product;

import com.amalitec.amalitececom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Transactional(readOnly = true)
    @QueryMapping
    public List<Product> getAllProducts() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);

        logger.info("Getting all products...");
        List<Product> products = productService.getAllProducts();
        logger.info("Retrieved {} products: {}", products.size(), products);
        return products;
    }

    //getProductById(id: UUID!): Product
    @Transactional(readOnly = true)
    @QueryMapping
    public Product getProductById(@Argument UUID id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // createProduct(input: CreateProductInput!) : Product!
    @Transactional
    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {

        try {
            // Extract values from input
            String name = input.getName();
            int stock = input.getStock();
            double price = input.getPrice();
            logger.info("Creating product: name={}, stock={}, price={}", name, stock, price);

            // Create a new Product
            Product newProduct = new Product(name, stock, price);
            Product savedProduct = productService.createProduct(newProduct);
            logger.info("Product created successfully: {}", savedProduct);
            return savedProduct;
        } catch (Exception e) {
            logger.error("Error creating product", e);
            throw e; // Rethrow the exception to propagate it to the caller
        }
    }

    // updateProductRating(input: UpdateProductInput!) : Product!
    @Transactional
    @MutationMapping
    public Product updateProduct(@Argument UpdateProductInput input) {

        UUID id = input.getId();
        int stock = input.getStock();
        double price = input.getPrice();

        Product product = getProductById(id);
        product.setPrice(price);
        product.setStock(stock);
        return productService.updateProduct(id,product);

    }

    // deleteProduct(id: ID!) : UUID!
    @Transactional
    @MutationMapping
    public DeletionStatus deleteProduct(@Argument UUID id) {

        logger.info("product with id: {} being deleted",id);

            if (!productService.existsById(id)) {
                logger.info("product with id: {} not found",id);
                return new DeletionStatus(false, "Product not found");
            }
            productService.deleteProduct(id);
        logger.info("product with id: {} deleted successfully",id);
        return new DeletionStatus(true, "Product deleted successfully");

    }

    @Transactional(readOnly = true)
    @QueryMapping
    public List<Product> getLowStockItems() {
        int threshold = 10;
        return productService.getLowStockItems(threshold);
    }
}
