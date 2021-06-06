package service.book.find;

import model.Book;

import java.util.ArrayList;

public interface IFind {
    ArrayList<Book> findBook (ArrayList<Book> books, String nameBook, int category_id);
}
