package com.katusoft.despensa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katusoft.despensa.dto.ProductRequest;
import com.katusoft.despensa.dto.ProductResponse;
import com.katusoft.despensa.model.Product;
import com.katusoft.despensa.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getNombre(),
                        product.getPrecio(),
                        product.getCantidad(),
                        product.getFechaVencimiento().toString(),
                        product.getFechaIngreso().toString()
                ))
                .toList();
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setNombre(productRequest.getNombre());
        product.setPrecio(productRequest.getPrecio());
        product.setCantidad(productRequest.getCantidad());
        product.setFechaVencimiento(productRequest.getFechaVencimiento());
        product.setFechaIngreso(productRequest.getFechaIngreso());

        productRepository.save(product);
    }

}
