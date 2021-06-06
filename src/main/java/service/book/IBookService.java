package service.book;

import model.Book;
import service.IService;
import service.book.find.IFind;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookService extends IService<Book> {


    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException;

    public void create (Book newBook, int[] categoryId) throws SQLException;

    public ArrayList<Book> getBookListById(int idOfPM) throws SQLException;

}
