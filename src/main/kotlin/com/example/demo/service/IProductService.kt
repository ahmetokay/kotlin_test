package com.example.demo.service

import com.example.demo.entity.Product

interface IProductService {
    fun createProduct(request: Product): Product
    fun getAllProducts(): List<Product>
    fun receiveProductById(productId: Long): Product
    fun updateProductById(productId: Long, request: Product): Product
    fun deleteProductById(productId: Long)
}