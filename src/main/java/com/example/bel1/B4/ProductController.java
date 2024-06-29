package com.example.bel1.B4;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {
    List<Product> productList = new ArrayList<>();

    @PostConstruct
    public void loadDataProduct() {
        productList.add(new Product((long) productList.size() + 1, "Product1", 1.0, 1));
        productList.add(new Product((long) productList.size() + 1, "Product2", 2.0, 2));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId((long) productList.size() + 1);
        productList.add(product);
        return product;
    }

    @GetMapping
    public List<Product> getProductList() {
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        Optional<Product> productOptional = productList.stream().filter(p -> p.getId().equals(id)).findFirst();
        return productOptional.orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        Optional<Product> productOptional = productList.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (productOptional.isPresent()) {
            Product productUpdate = productOptional.get();
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setQuantity(product.getQuantity());
            return productUpdate;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id){
        Optional<Product> productOptional = productList.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (productOptional.isPresent()){
            productList.remove(productOptional.get());
            return "Product deleted";
        }else {
            return "Product not found";
        }
    }
}
