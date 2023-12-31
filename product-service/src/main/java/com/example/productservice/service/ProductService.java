package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
      return productRepository.findAll()
              .stream()
              .map(this::mapToProductResponse)
              .toList();
//        List<Product> products = productRepository.findAll();
//        List<ProductResponse> productResponses = null;
//        for (Product product:products) {
//            productResponses.add(ProductResponse.builder()
//                            .id(product.getId())
//                            .name(product.getName())
//                            .description(product.getDescription())
//                            .price(product.getPrice())
//                            .build());
//        }
//        return productResponses;
    }

    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
