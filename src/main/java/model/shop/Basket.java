package model.shop;

import services.BasketService;

import java.util.HashMap;

public class Basket {
    private int userId;
    private HashMap<Product, Integer> products;

    public Basket(int userId) {
        this.userId = userId;
        this.products = new HashMap<>();

    }


    public void addProduct(Product product) {


    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void setBasketFromDB() {
        BasketService basketService = new BasketService(this.userId);
        basketService.setProductsFromDB();
        this.products = basketService.getBasket().get(0);


    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "userId=" + userId +
                ", products=" + products +
                '}';
    }
}
