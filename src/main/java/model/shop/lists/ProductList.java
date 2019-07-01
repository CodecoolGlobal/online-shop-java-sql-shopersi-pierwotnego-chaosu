package model.shop.lists;

import model.shop.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    List<Product> products;

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

    public List<Product> getProducts() {
        return this.products;
    }

    public boolean isIdValid(int id){
        //checks if product with given id exists
        for (Product product: products
             ) { if (product.getId() == id) return true;
        }
        return false;
    }

    public Product getProductById(int id){
        //returns the product of given id
        for (Product product: products
        ) { if (product.getId() == id) return product;
        }
        return null;
    }

    public void removeAmountById(int id, int amount){
        //removes amount from product of given id
        Product product = getProductById(id);
        product.setAmount(product.getAmount()-amount);
    }

    public void updateAllProductsInDataBase(){
        for (Product product: products
        ) { product.update();
        }
    }

    //updateAllProducts

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
