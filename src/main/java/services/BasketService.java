package services;

import dao.sql.BasketsDAO;
import model.shop.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketService {
    private ArrayList<Product> basket;
    private HashMap<Product, Integer> productsCounted;

//    private int productId;
    private int userId;
//    private int amount;

    public BasketService(int userId) {
        this.basket = new ArrayList<>();
        this.productsCounted = countProducts();
//        this.productId = productId;
        this.userId = userId;
//        this.amount = amount;
    }

    private HashMap<Product, Integer> countProducts() {
        HashMap<Product, Integer> productsM = new HashMap<>();

        for (Product product : this.basket) {
//            Integer id = product.getId();
            if (productsM.containsKey(product)) {
                Integer counter = productsM.get(product);
                counter++;
                productsM.replace(product, counter);
            }
            if (!productsM.containsKey(product)) {
                productsM.put(product, 1);
            }

        }
        return productsM;
    }

    public void setProductsFromDB() {
        BasketsDAO basketsDAO = new BasketsDAO();
        basketsDAO.setUserId(this.userId);
        this.basket = basketsDAO.read();
    }
}

