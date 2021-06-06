package service.book.find;

import model.Book;

import java.util.ArrayList;

public class FindByName implements IFind {

    @Override
    public ArrayList<Book> findBook(ArrayList<Book> books, String nameBook, int category_id) {
        ArrayList<Book> bookList = new ArrayList<>();
        for (Book b :books ) {
            if(b.getName().equals(nameBook)){
                bookList.add(b);
            }

        }
        return bookList;

    }
}
