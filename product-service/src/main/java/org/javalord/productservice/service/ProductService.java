package org.javalord.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javalord.productservice.dto.ProductRequest;
import org.javalord.productservice.model.Product;
import org.javalord.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        Product saved = productRepository.save(product);
        log.info("Product {} created", product);

        return saved;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
