package com.gbz.yaps.petstore.ejb.stateless.catalog;

import java.util.List;

import javax.ejb.Remote;

import com.gbz.yaps.petstore.entity.catalog.Category;
import com.gbz.yaps.petstore.entity.catalog.Item;
import com.gbz.yaps.petstore.entity.catalog.Product;

@Remote
public interface CatalogRemote {
    Category createCategory(Category category);

    Category findCategory(Long categoryId);

    void deleteCategory(Category category);

    Category updateCategory(Category category);

    List<Category> findCategories();

    Product createProduct(Product product, Category category);

    Product findProduct(Long productId);

    void deleteProduct(Product product);

    Product updateProduct(Product product, Category category);

    List<Product> findProducts();

    Item createItem(Item item, Product product);

    Item findItem(Long itemId);

    void deleteItem(Item item);

    Item updateItem(Item item, Product product);

    List<Item> findItems();
}
