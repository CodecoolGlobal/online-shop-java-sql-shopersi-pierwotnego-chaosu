package model.shop.lists;

import model.shop.Order;

import java.util.ArrayList;

public class OrdersList {
    private ArrayList<Order> orders;

    public OrdersList(){}

    public OrdersList(ArrayList<Order> orders){this.orders = orders;}

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
