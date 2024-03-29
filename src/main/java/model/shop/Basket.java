package model.shop;

import dao.sql.BasketsDAO;
import model.services.BasketService;
import model.shop.abc.ProductList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private int userId;
    private HashMap<Product, Integer> products;

    public Basket(int userId) {
        this.userId = userId;
        this.products = new HashMap<>();

    }


    public void addProductToBasket(int prodId, List<Product> productList, int quantity) {

        for (Product prod : productList) {
            if (prodId == prod.getId()) {

                if (products.containsKey(prod)) {
                    int count = products.get(prod);
                    count += quantity;
                    products.replace(prod, count);
                } else products.put(prod, quantity);
            }
        }
    }


    public Double countTotalValue() {
        Double totalValue = 0.0;

        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            totalValue += key.getPrice() * value;
        }


        return totalValue;
    }


    public void setBasketFromDB() {
        BasketService basketService = new BasketService(this.userId);
        basketService.setProductsFromDB();


        if (basketService.getBasket().size() == 0) {
            this.products = new HashMap<>();
        } else {
            this.products = basketService.getBasket().get(0);
        }
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "userId=" + userId +
                ", products=" + products +
                '}';
    }

    public void updateToDB() {

        BasketsDAO basketsDAO = new BasketsDAO();

        this.products.forEach((product, amount) -> basketsDAO.create(userId, product, amount));


    }

    public void updateProductList(ProductList productList) {
        if (!this.products.isEmpty()) {
            productList.getProducts().forEach((product) -> {

                if (this.products.get(product) != null) {

                    int amount = product.getAmount();
                    product.setAmount(amount - this.products.get(product));

                }
            });
        }

    }
}
