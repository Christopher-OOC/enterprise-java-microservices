package org.javalord.productservice.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javalord.productservice.dto.ProductRequest;
import org.javalord.productservice.model.Product;
import org.javalord.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }

}
