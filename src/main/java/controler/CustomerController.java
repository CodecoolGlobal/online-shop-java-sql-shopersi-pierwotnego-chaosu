package controler;


import dao.sql.ProductDAO;
import model.shop.Basket;
import model.shop.User;

import model.shop.abc.ProductList;
import view.Display;

import java.util.Scanner;

public class CustomerController {

    private User user;
    private Basket basket;
    private ProductList productList;

    public CustomerController(User user) {
        this.user = user;
        this.basket = new Basket(user.getId());
        this.productList = new ProductList(new ProductDAO().read());
    }


    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {



            Display.clearScreen();
            Display.showMenu("customerMenu");


            switch (scanner.nextInt()) {

                case 1: {

                    Display.clearScreen();
                    Display.printProductTable(productList);
                    Display.prompt();
                    break;
                }

                case 2: {
                    Display.clearScreen();
                    Display.printProductTable(productList);
                    addProdToB();

                    break;
                }
                case 3: {
                    Display.printBasket(basket);
                    Display.prompt();
                    break;
                }
                case 4: {
                    Display.printBasket(basket);
                    editBasket();
                    Display.prompt();
                    break;
                }
                case 8: {
                    basket.setBasketFromDB();
                    break;
                }
                case 0: {
                    isRunning = false;
                    break;
                }
                default: {
                }

            }
        }
    }

    private void editBasket() {




    }

    private void addProdToB(){
        boolean isAsking = true;
        while (isAsking) {

            int prodId = Display.askForInt("Select productID");
            boolean isValid = productList.isIdValid(prodId);

            if (isValid){
                int quantity = Display.askForInt("How many Items?");

                if (quantity>0 && quantity<= productList.getProductById(prodId).getAmount()){

                    basket.addProductToBasket(prodId,this.productList.getProducts(), quantity);
                    isAsking = false;
                } else System.out.println("r Amount");

            } else System.out.println( "Incorrect Id");

        }
        Display.prompt();
    }

}
