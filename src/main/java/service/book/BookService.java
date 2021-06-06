package service.book;

import model.Book;
import model.Category;
import model.NXB;
import model.StatusBook;
import service.book.find.FindByCategory;
import service.book.find.FindByName;
import service.book.find.FindByNameAndCategory;
import service.book.find.IFind;
import service.category.CategoryService;
import service.category.ICategoryService;
import service.connection.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService extends ABookService implements  IFind {


    public static final String INSERTINTORELATIVE = "insert into relative(bookID, categoryID) value(?,?)";
    public static final String findBookByID = "select * from book where id=?";
    public static final String insertBook = "insert into book(name, description, status_id, nxb_id, urlOfImage) value (?,?,?,?,?)";
    public static final String insertBooK_Category = "insert into book_category(book_id, category_id) value(?,?)";
    public static final String EDITBOOK = "update  book set name= ?, description = ?, status_id =?, nxb_id=?,urlOfImage=? where id= ?;";
    public static final String DELETEBOOK_CATEGORY = "delete from book_category where book_id =?";
    public static final String Delete_Book = "delete from book where id=?;";

    ICategoryService categoryService = new CategoryService();


    public static final String CALLGETALLBOOK= "call getAllBook()";
    Connection connection = ConnectionJDBC.getConnect();



    @Override
    public Book getObjectById (int id) throws SQLException {
        Book oldBook = null;
        PreparedStatement statement = connection.prepareStatement(findBookByID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){

            String  name = result.getString("name");
            String  description = result.getString("description");
            String  urlOfImage = result.getString("urlOfImage");

            oldBook = new Book (name, description, urlOfImage);
        }

        return oldBook;
    }

    @Override
    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException {

            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(EDITBOOK);
            statement.setString(1,updateBook.getName());
            statement.setString(2,updateBook.getDescription());
            statement.setInt(3,updateBook.getStatusBook().getId());
            statement.setInt(4,updateBook.getNxb().getId());
            statement.setString(5,updateBook.getUrlOfImage());
            statement.setInt(6,id);
            statement.executeUpdate();

            PreparedStatement statement1 = connection.prepareStatement(DELETEBOOK_CATEGORY);
            statement1.setInt(1,id);
            statement1.executeUpdate();

            PreparedStatement statement2 = connection.prepareStatement(insertBooK_Category);
            for (int i = 0; i < categories_int.length; i++) {

                statement2.setInt(1,id);
                statement2.setInt(2, categories_int[i]);
                statement2.executeUpdate();
            }


    }

    @Override
    public void create(Book newBook, int[] categoryId) throws SQLException {


     connection.setAutoCommit(false);

     PreparedStatement statement = connection.prepareStatement(insertBook, PreparedStatement.RETURN_GENERATED_KEYS );
     statement.setString(1, newBook.getName());
     statement.setString(2, newBook.getDescription());
     statement.setInt(3, newBook.getStatusBook().getId());
     statement.setInt(4, newBook.getNxb().getId());
     statement.setString(5, newBook.getUrlOfImage());

     statement.executeUpdate();
     int id=0;
     ResultSet result  = statement.getGeneratedKeys();
        while(result.next()){
            id = result.getInt(1);
        }

     PreparedStatement statement1 = connection.prepareStatement(insertBooK_Category);

        for (int i = 0; i < categoryId.length; i++) {
            statement1.setInt(1, id);
            statement1.setInt(2, categoryId[i]);
            statement1.executeUpdate();
        }

     connection.commit();


    }




    @Override
    public ArrayList<Book> findAll() throws SQLException {
        ArrayList<Book> bookList = new ArrayList<>();

        CallableStatement statement =connection.prepareCall(CALLGETALLBOOK);

        ResultSet result = statement.executeQuery();

        while(result.next()){
            int idBook = result.getInt(1);
            String nameBook = result.getString(2);
            String description = result.getString(3);
            String status = result.getString(4);
            String nxb = result.getString(5);


            NXB nxbObject = new NXB(nxb);
            StatusBook statusBook= new StatusBook(status);

            ArrayList<Category> categories = (ArrayList<Category>) categoryService.findCategoryByID(idBook);

            Book book = new Book(idBook,nameBook,description ,nxbObject, statusBook, categories );

            bookList.add(book);

        }

        return bookList;

    }

    @Override
    public void creat(Book newE) {

    }

    @Override
    public void edit(int id, Book book) {

    }

    @Override
    public void delete(int index) throws SQLException {

        connection.setAutoCommit(false);

        PreparedStatement statement1 = connection.prepareStatement(DELETEBOOK_CATEGORY);
        statement1.setInt(1,index);
        statement1.executeUpdate();

        PreparedStatement statement2 = connection.prepareStatement(Delete_Book);
        statement2.setInt(1,index);
        statement2.executeUpdate();

        connection.commit();

    }


    @Override
    public ArrayList<Book> getBookListById(int idOfPM) throws SQLException {
        ArrayList<Book> bookList= new ArrayList<>();

        CallableStatement statement = connection.prepareCall("call getListBookByIdOfPM(?);");
        statement.setInt(1, idOfPM);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int id =resultSet.getInt(1);
            String  bookName= resultSet.getString(2);
            Book book = new Book(id, bookName);

            bookList.add(book);
            System.out.println("");

        }

        return bookList;


    }


    @Override
    public ArrayList<Book> findBook(ArrayList<Book> books, String nameBook, int category_id) {
//        ArrayList<Book> bookList = new ArrayList<>();
        if(nameBook =="" & category_id!=0){
             IFind findBook = new FindByCategory();
             return findBook.findBook(books, nameBook, category_id);

        }else if(nameBook !="" & category_id==0){
            IFind findBook = new FindByName();
            return findBook.findBook(books, nameBook, category_id);

        }else if(nameBook !="" & category_id!=0) {
            IFind findBook = new FindByNameAndCategory();
            return findBook.findBook(books, nameBook, category_id);
        }else return null;

    }
}
