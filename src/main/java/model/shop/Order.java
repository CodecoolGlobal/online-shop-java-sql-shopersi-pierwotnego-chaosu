package model.shop;

import dao.sql.OrdersDAO;

import java.time.LocalDate;
import java.util.HashMap;

public class Order {
    private int id;
    private int userId;
    private String status;
    private LocalDate date = LocalDate.now();
    private HashMap<Product, Integer> orderItemsList;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatusAsSubmit() {
        this.status = "Submit";
    }
    public void setStatusAsPaid() {
        this.status = "Paid";
    }
    public void setStatusAsOnTheWay() {
        this.status = "On the way";
    }
    public void setStatusAsDelivered() {
        this.status = "Delivered";
    }
    public void makeNewOrder(User user, Basket basket){
        new OrdersDAO().create(user);
    }

    public void makeOrderedItemsList(Basket basket){
            this.orderItemsList = basket.getProducts();}

    public HashMap<Product, Integer> getOrderItemsList() {
        return orderItemsList;
    }




}
