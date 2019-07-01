package model.shop.lists;

import model.shop.OrderItem;
import model.shop.Product;

import java.util.ArrayList;

public class OrderItemsList {

    private ArrayList<OrderItem> orderItems;

    public OrderItemsList() {
    }

    public OrderItemsList(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void add(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void removeFromList(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public void delete(OrderItem orderItem) {
        removeFromList(orderItem);
//        orderItem.delete();   <<<----------DAO delete like in ProductDAO to delete from DATABASE
    }

    public ArrayList<OrderItem> getProducts() {
        return this.orderItems;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + orderItems +
                '}';
    }
}
