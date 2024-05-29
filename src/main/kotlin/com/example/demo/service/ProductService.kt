package com.example.demo.service

import com.example.demo.entity.Product
import com.example.demo.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository) : IProductService {

    private val PRODUCT_NOT_FOUND_MESSAGE : String = "Product Not Found with this Product ID !"

    override fun createProduct(request: Product): Product {
        return productRepository.save(request)
    }

    override fun getAllProducts(): List<Product> {
        return productRepository.findAll().toList()
    }

    override fun receiveProductById(productId: Long): Product {
        val validProduct = findById(productId)
        val isProductPresent = validProduct.isPresent

        if (isProductPresent) {
            return validProduct.get()
        } else throw RuntimeException(PRODUCT_NOT_FOUND_MESSAGE)

    }

    override fun updateProductById(productId: Long, request: Product): Product {
        val validProduct = findById(productId).orElseThrow{throw RuntimeException(PRODUCT_NOT_FOUND_MESSAGE)}

        try {
            return productRepository.save(validProduct)
        } catch (exception : Exception) {
            throw RuntimeException("Product Could Not Be Updated !")
        }
    }

    override fun deleteProductById(productId: Long) {
        val validProduct = findById(productId).orElseThrow{throw RuntimeException(PRODUCT_NOT_FOUND_MESSAGE)}

        try {
            productRepository.delete(validProduct)
        } catch (exception : Exception) {
            throw RuntimeException("Product Could Not Be Deleted !")
        }
    }

    private fun findById(productId: Long) : Optional<Product> {
        return productRepository.findById(productId)
    }
}