package com.wcd.assignment.model;

import com.wcd.assignment.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product obj); // lưu thông tin.

    List<Product> findAll();

    Product findById(int id);

    Product update(int id, Product updateObj);

    boolean delete(int id);
}
