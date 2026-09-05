package com.katusoft.despensa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katusoft.despensa.dto.ProductRequest;
import com.katusoft.despensa.dto.ProductResponse;
import com.katusoft.despensa.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/productos")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<String> creteProduct(@RequestBody ProductRequest product) {
        productService.createProduct(product);
        
        return ResponseEntity.ok("Producto creado exitosamente");
    }
    

}
