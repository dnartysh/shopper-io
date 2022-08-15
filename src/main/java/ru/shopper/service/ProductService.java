package ru.shopper.service;

import org.springframework.stereotype.Service;
import ru.shopper.model.Product;
import ru.shopper.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);

        return products;
    }

    public void addProduct(String name, Double price, Double weight, String description) {
        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setWeight(weight);
        product.setDescription(description);

        productRepository.save(product);
    }
}
