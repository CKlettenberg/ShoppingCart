package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {
    private final ProductRepository productRepository;
    private final double TAX_RATE = 22.00;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getCartItems(){
        List<Product> all = productRepository.findAll();
        return all;
    }

    public void removeProduct(String name){
        productRepository.deleteByName(name);
    }

    public double calculateTax(){
        return 1 * TAX_RATE / 100;
    }

    @Scheduled(fixedRate = 10000)
    public double calculateCartTotal(){
        List<Product> products = productRepository.findAll();
        double total = 0;
        for (Product product : products) {
            total += product.calculateTotal();
        }

        System.out.println("Scheduler total: " + (total*calculateTax()));
        return total - (total * calculateTax());
    }
}
