package service.book.find;

import model.Book;
import model.Category;

import java.util.ArrayList;

public class FindByNameAndCategory implements IFind{

    @Override
    public ArrayList<Book> findBook(ArrayList<Book> books, String nameBook, int category_id) {
        ArrayList<Book> bookList = new ArrayList<>();
        for (Book b :books ) {
            if(b.getName().equals(nameBook)){
                for (Category c: b.getCategories()) {
                    if(c.getId()== category_id){
                        bookList.add(b);
                    }
                }
            }
        }
        return bookList;
    }
}
