package model.shop;

public class OrdersItems {
    private int id;
    private int orderId;
    private int itemId;
    private int amount;
    private float price;

    public OrdersItems (int id, int orderId, int itemId, int amount, float price){
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrdersItems{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", itemId=" + itemId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
