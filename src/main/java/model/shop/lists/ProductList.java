package model.shop.lists;

import model.shop.Product;

import java.util.ArrayList;

public class ProductList {
    ArrayList<Product> products;

    public ProductList() {
    }

    public ProductList(ArrayList<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public void removeFromList(Product product) {
        products.remove(product);
    }

    public void delete(Product product) {
        removeFromList(product);
        product.delete();
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
