package com.example.demo.controller

import com.example.demo.entity.Product
import com.example.demo.service.IProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService : IProductService) {

    @PostMapping
    fun createProduct(@RequestBody req : Product) : ResponseEntity<Product> {
        val savedProduct = productService.createProduct(req)
        return ResponseEntity(savedProduct, HttpStatus.CREATED)
    }

    @GetMapping
    fun receiveAllProducts() : ResponseEntity<List<Product>> {
        val allProducts = productService.getAllProducts()
        return ResponseEntity.ok(allProducts)
    }

    @GetMapping("/{productId}")
    fun receiveProduct(@PathVariable productId: Long) : ResponseEntity<Product>  {
        val validProduct = productService.receiveProductById(productId)
        return ResponseEntity.ok(validProduct)
    }

    @PutMapping("/{productId}")
    fun updateProduct(@PathVariable productId : Long, @RequestBody req : Product) : ResponseEntity<Product> {
        val updatedProduct = productService.updateProductById(productId, req)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long) : ResponseEntity<Product> {
        productService.deleteProductById(productId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}