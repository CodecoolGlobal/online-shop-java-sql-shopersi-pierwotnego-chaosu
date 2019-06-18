package model.shop;

import java.util.ArrayList;
import java.util.Iterator;

public class Basket {
    private int id;
    private Iterator iterator; //TODO
    private ArrayList<Product> products;

    public Basket(int id) {
        this.id = id;
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }






}
