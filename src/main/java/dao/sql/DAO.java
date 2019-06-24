package dao.sql;

import model.shop.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO {
    void create();
    List read();
    void update();
    void delete();
}
