package model.shop.lists;

import model.shop.Order;
import model.shop.User;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList(){}

    public UserList(ArrayList<User> orders){this.users = orders;}

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setOrders(ArrayList<User> users) {
        this.users = users;
    }
}
