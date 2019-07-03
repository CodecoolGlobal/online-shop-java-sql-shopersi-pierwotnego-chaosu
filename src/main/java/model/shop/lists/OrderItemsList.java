package model.shop.lists;

import dao.sql.OrdersItemsDAO;
import model.shop.OrderItem;
import model.shop.Product;
import model.shop.abc.ProductList;

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

    public ArrayList<OrderItem> getItems() {
        return this.orderItems;
    }

    public int totalPrice (ProductList products) {
        int totalPrice = 0;
        for (Product product : products.getProducts()) {
            totalPrice += (product.getPrice() * product.getAmount());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + orderItems +
                '}';
    }
}
