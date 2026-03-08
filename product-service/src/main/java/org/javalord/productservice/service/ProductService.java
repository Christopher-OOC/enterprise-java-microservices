package org.javalord.productservice.service;

import lombok.RequiredArgsConstructor;
import org.javalord.productservice.dto.ProductRequest;
import org.javalord.productservice.model.Product;
import org.javalord.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
    }

}
