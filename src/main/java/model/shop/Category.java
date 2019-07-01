package model.shop;

import dao.sql.CategoryDAO;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;

    public Category(String name) {
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category{" +
                "id= " + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void createInDB(){
        new CategoryDAO().create(this);
    }

    public void updateInDB(){
        new CategoryDAO().update(this);
    }

    public void setId(int id) {
        this.id = id;
    }
}
