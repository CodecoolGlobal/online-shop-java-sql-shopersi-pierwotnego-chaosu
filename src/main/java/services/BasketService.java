package services;

import dao.sql.BasketsDAO;
import model.shop.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BasketService {
    private ArrayList<HashMap<Product, Integer>> basket;
    private int userId;

    public BasketService(int userId) {
        this.basket = new ArrayList<>();
//        setProductsFromDB();
        this.userId = userId;
    }



    public void setProductsFromDB() {
        BasketsDAO basketsDAO = new BasketsDAO();
        basketsDAO.setUserId(this.userId);
        this.basket = basketsDAO.read();
    }

    public ArrayList<HashMap<Product, Integer>> getBasket() {
        return basket;
    }
}

