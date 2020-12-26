package com.slipper.SpringWebApp.repositories;

import com.slipper.SpringWebApp.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Интерфейс репозитория продуктов
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Поиск одного продукта по названию
    Product findOneByTitle(String title);
    //List<Product> findAllByPriceBetweenAndTitle(int min, int max, String title); //например то шо умеет springdata

    // Поиск всех по цене больше чем value и т.д.
    List<Product> findAllByPriceGreaterThan(int value);
    List<Product> findAllByPriceLessThan(int value);
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByIdAsc();

    // Поиск всех продуктов с правилом сортировки sort
    List<Product> findAll(Sort sort);

}
