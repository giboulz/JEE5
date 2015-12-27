package com.gbz.yaps.petstore.ejb.stateless.catalog;

import java.util.List;

import javax.ejb.Local;

import com.gbz.yaps.petstore.entity.catalog.Category;
import com.gbz.yaps.petstore.entity.catalog.Item;
import com.gbz.yaps.petstore.entity.catalog.Product;

@Local
public interface CatalogLocal {
    Category findCategory(Long categoryId);

    Product findProduct(Long productId);

    Item findItem(Long itemId);

    List<Item> searchItems(String keyword);
}
