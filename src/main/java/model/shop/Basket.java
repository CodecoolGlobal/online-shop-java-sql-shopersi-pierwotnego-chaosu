package model.shop;

import services.BasketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Basket {
    private int userId;
    private HashMap<Product, Integer> products;

    public Basket(int userId) {
        this.userId = userId;
        this.products = new HashMap<>();

    }


    public void addProductToBasket(int prodId, ArrayList<Product> productList, int quantity) {

        for (Product prod : productList) {
            if (prodId == prod.getId()) {

                if (products.containsKey(prod)) {
                    int count = products.get(prod);
                    count+= quantity;
                    products.replace(prod, count);
                } else products.put(prod, quantity);
            }

        }

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
