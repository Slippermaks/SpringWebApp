package com.slipper.SpringWebApp.services;

import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.repositories.ProductRepository;
import com.slipper.SpringWebApp.utils.discount.Discount;
import com.slipper.SpringWebApp.utils.discount.DiscountFactory;
import com.slipper.SpringWebApp.utils.discount.DiscountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

// Сервис продуктов
@Service
public class ProductService {
    private ProductRepository productRepository;

    // Привязка репозитория продуктов
    @Autowired
    public void setProductRepository(ProductRepository productRepository) { this.productRepository = productRepository; }

    // Получение всех продуктов
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    public List<Product> getAllProductsOrderByPriceAsc() {
        return productRepository.findByOrderByPriceAsc();
    }

    public List<Product> getAllProductsOrderByIdAsc() {
        return productRepository.findByOrderByIdAsc();
    }

    // Получение продукта по ID
    public Product getProductById(Long id){ return productRepository.findById(id).get(); }

    // Удаление продукта по ID
    public void deleteProductById(Long id){ productRepository.deleteById(id); }

    // Получение продукта по названию
    public Product getProductByTitle(String title) { return productRepository.findOneByTitle(title); }

    public List<Product> getAllProductsByPriceGreaterThen(int value) { return productRepository.findAllByPriceGreaterThan(value); }

    public List<Product> getAllProductsByPriceLessThen(int value) { return productRepository.findAllByPriceLessThan(value); }

    // Сохранение продукта в БД
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Получение отсортированного списка продуктов
    public List<Product> getSorted(String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        return productRepository.findAll(sort);
    }

}
