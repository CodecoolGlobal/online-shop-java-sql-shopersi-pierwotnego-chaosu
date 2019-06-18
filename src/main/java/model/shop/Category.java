package model.shop;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private boolean isAvailable = true;
    private ArrayList<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<Product>();

    }

    public void addProduct(Product product){products.add(product);}

    public void removeProductByItsId(int id){
        for (Product product: products
             ) { if (product.getId() == id) products.remove(product);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
