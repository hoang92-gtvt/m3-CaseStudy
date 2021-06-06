package model;

import java.util.ArrayList;

public class Book {
    private  int id;
    private String name;
    private String description;
    private NXB nxb;
    private StatusBook statusBook;
    private String urlOfImage;
    ArrayList<Category> categories;

    public Book() {
    }

    public StatusBook getStatusBook() {
        return statusBook;
    }

    public void setStatusBook(StatusBook statusBook) {
        this.statusBook = statusBook;
    }

    public Book(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Book(int id, String name, String description, NXB nxb, StatusBook statusBook, String urlOfImage, ArrayList<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nxb = nxb;
        this.statusBook = statusBook;
        this.urlOfImage = urlOfImage;
        this.categories = categories;
    }

    public Book(int id, String name, String description, NXB nxb, StatusBook status, String urlOfImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nxb = nxb;
        this.urlOfImage = urlOfImage;
        this.statusBook= status;
    }

    public Book(int id, String name, String description, NXB nxb, StatusBook statusBook,  ArrayList<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nxb = nxb;
        this.statusBook = statusBook;
        this.urlOfImage = urlOfImage;
        this.categories = categories;
    }

    public Book(String name, String description, NXB nxb, StatusBook statusBook, String urlOfImage) {
        this.name = name;
        this.description = description;
        this.nxb = nxb;
        this.statusBook = statusBook;
        this.urlOfImage = urlOfImage;
    }

    public Book(String name, String description, String urlOfImage) {
        this.name = name;
        this.description = description;
        this.urlOfImage = urlOfImage;
    }

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NXB getNxb() {
        return nxb;
    }

    public void setNxb(NXB nxb) {
        this.nxb = nxb;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public void setUrlOfImage(String urlOfImage) {
        this.urlOfImage = urlOfImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
