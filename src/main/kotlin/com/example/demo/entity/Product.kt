package com.example.demo.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var price: BigDecimal,
    var createdDate: LocalDateTime? = null,
    var updatedDate: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        createdDate = LocalDateTime.now()
        updatedDate = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedDate = LocalDateTime.now()
    }
}