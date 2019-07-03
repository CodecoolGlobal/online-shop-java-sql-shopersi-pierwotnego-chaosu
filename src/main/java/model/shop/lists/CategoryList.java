package model.shop.lists;

import dao.sql.CategoryDAO;
import model.shop.Category;

import java.util.List;

public class CategoryList {


    private List<Category> categoryList;

    public CategoryList(){
        this.categoryList = new CategoryDAO().readCategoryList();
    }

    public Category getCategoryById(int id){
        for (Category category: categoryList
             ) { if (category.getId() == id) return category;
        }
        return null;
    }

    public String getCategoryNameById(int id){
        return getCategoryById(id).getName();
    }

    public boolean checkIfCategoryIdExist(int id){
        for (Category category: categoryList
        ) { if (category.getId() == id) return true;
        }
        return false;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
    @Override
    public String toString() {
        String list = "";
        for (Category category: categoryList
             ) {list += category.toString() + "\n";

        }
        return list;
    }
}
