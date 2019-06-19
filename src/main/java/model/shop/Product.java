package model.shop;

public class Product {
    private int id;
    private String name;
    private float price;
    private int amount;
    private boolean isAvailable;
    private int category;

    public Product(int id, String name, float price, int amount, boolean isAvailable, int category) {
        this.id = id;
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

    public float getPrice() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        if (this.amount <= 0) setAvailable(false);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", isAvailable=" + isAvailable +
                ", category=" + category +
                '}';
    }
}
