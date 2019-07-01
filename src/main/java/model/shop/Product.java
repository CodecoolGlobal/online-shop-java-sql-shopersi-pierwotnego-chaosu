package model.shop;

import dao.sql.ProductDAO;
import model.shop.lists.CategoryList;

public class Product {
    private int id;
    private String name;
    private double price;
    private int amount;

    private boolean isAvailable;
    private int category;

    public Product(String name, double price, int amount, boolean isAvailable, int category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCategory() {
        return category;
    }

    public String getCategoryName(){
        return new CategoryList().getCategoryNameById(this.category);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
//        update();
    }

    public void setPrice(double price) {
        this.price = price;
//        update();
    }

    public void setAmount(int amount) {
        this.amount = amount;
//        update();
        if (this.amount <= 0) setAvailable(false);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
//        update();
    }

    public void setCategory(int category) {
        this.category = category;
//        update();
    }

    public void update(){
        new ProductDAO().update(this);
    }

    public void delete(){
        new ProductDAO().delete(this);
    }

    public void create(){
        new ProductDAO().create(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", amount=" + amount +
//                ", isAvailable=" + isAvailable +
//                ", category=" + category +
                '}';
    }
}
