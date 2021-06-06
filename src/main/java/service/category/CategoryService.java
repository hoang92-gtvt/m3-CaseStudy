package service.category;

import model.Book;
import model.Category;
import service.connection.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{

    public static final String CALL_GET_CATEGORY_BY_ID_BOOK = "call getCategoryByIdBook(?)";
    public static final String SELECT_ALLCATEGORY = "select * from category;";
    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public ArrayList<Category> findAll() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALLCATEGORY);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            int id = result.getInt(1);
            String name = result.getString(2);
            Category category= new Category(id, name);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public void creat(Category newE) {

    }

    @Override
    public void edit(int index, Category newE) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public Category getObjectById(int id) throws SQLException {
        return null;
    }


    @Override
    public List<Category> findCategoryByID(int idOfBook) throws SQLException {
        List<Category> categories= new ArrayList<>();

        CallableStatement statement = connection.prepareCall(CALL_GET_CATEGORY_BY_ID_BOOK);
        statement.setInt(1, idOfBook);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int category_id = resultSet.getInt(1);
            String  categoryName = resultSet.getString(2);
            Category category = new Category(category_id,categoryName);

            categories.add(category);
            System.out.println("");

        }
        System.out.println(categories);
        return categories;
    }
}
