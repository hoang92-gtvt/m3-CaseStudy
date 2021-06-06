package controller;

import model.Book;
import model.Category;
import model.NXB;
import model.StatusBook;
import service.book.ABookService;
import service.book.BookService;
import service.book.IBookService;
import service.category.CategoryService;
import service.category.ICategoryService;
import service.nxb.INXBService;
import service.nxb.NXBService;
import service.statusBook.IStatusBookService;
import service.statusBook.StatusBookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ControllerBook", value ="/book")
public class ControllerBook extends HttpServlet {
    ABookService bookService = new BookService();
    ICategoryService categoryService = new CategoryService();
    INXBService nxbService = new NXBService();
    IStatusBookService statusBookService = new StatusBookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {

                case "create":
                    showFormCreate(request, response);
                    break;

                case "edit":
                    showFormEdit(request, response);
                    break;

                case "delete":
                    showFormDelete(request,response);

                case "customer":
                    ShowAllBookForCustomer(request,response);

                case "find":
                    ShowFormFind(request,response);

                default:
                    showAllBook(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ShowFormFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/find.jsp");
            ArrayList<Category> categories = categoryService.findAll();
            request.setAttribute("categories", categories);


        dispatcher.forward(request,response);

    }

    private void ShowAllBookForCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/list_customer.jsp");

        ArrayList<Book> bookList = new ArrayList<>();

        bookList = bookService.findAll();

        request.setAttribute("bookList", bookList);

        dispatcher.forward(request, response);

    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("books/delete.jsp");
//        request.setAttribute("id", id);
        dispatcher.forward(request,response);

    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<StatusBook> statusBooks = statusBookService.findAll();
        ArrayList<NXB> nxbList = nxbService .findAll();
        ArrayList<Category> categories = categoryService.findAll();

        int id  = Integer.parseInt(request.getParameter("id"));
        Book oldBook = bookService.getObjectById(id);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/formEdit.jsp");
        request.setAttribute("statusBooks", statusBooks);
        request.setAttribute("nxbList", nxbList);
        request.setAttribute("categories", categories);
        request.setAttribute("oldBook", oldBook);

        dispatcher.forward(request,response);

    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<StatusBook> statusBooks = statusBookService.findAll();
        ArrayList<NXB> nxbList = nxbService .findAll();
        ArrayList<Category> categories = categoryService.findAll();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/formCreate.jsp");
        request.setAttribute("statusBooks", statusBooks);
        request.setAttribute("nxbList", nxbList);
        request.setAttribute("categories", categories);


        dispatcher.forward(request,response);

    }

    private void showAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/list.jsp");

        ArrayList<Book> bookList = new ArrayList<>();

        bookList = bookService.findAll();

        request.setAttribute("bookList", bookList);

        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    Create(request, response);
                    break;

                case "edit":
                    Edit(request, response);
                    break;

                case "delete":
                    delete(request,response);
                    break;

                case "find":
                    find(request,response);

////
//            default:
//                showAllBook(request, response);
//                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String nameBook = request.getParameter("nameBook");
        String category_str = request.getParameter("category_id");
        int category_id =0;
        if(category_str!="") {
            category_id = Integer.parseInt(category_str);

        }

        ArrayList<Book> books = bookService.findAll();
        ArrayList<Book> bookList = bookService.findBook(books, nameBook, category_id);

        int role_id = Integer.parseInt(request.getParameter("role_id"));
        if(role_id==1){

            RequestDispatcher dispatcher = request.getRequestDispatcher("/books/list.jsp");
            request.setAttribute("bookList", bookList);
            dispatcher.forward(request,response);

        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/books/list_customer.jsp");
            request.setAttribute("bookList", bookList);
            dispatcher.forward(request,response);

        }


    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        bookService.delete(id);
        showAllBook(request, response);

    }

    private void Edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int status_id = Integer.parseInt(request.getParameter("status"));
        int nxb_id = Integer.parseInt(request.getParameter("nxb"));
        String urlOfImage = request.getParameter("urlOfImage");

        StatusBook status = new StatusBook(status_id);
        NXB nxb = new NXB(nxb_id);


        Book newBook = new Book(id,name,description,nxb,status, urlOfImage);


        String[] categoriesStr = request.getParameterValues("category");

        int[] categoriesInt= new int[categoriesStr.length];

        for (int i = 0; i < categoriesInt.length; i++) {
            categoriesInt[i]= Integer.parseInt(categoriesStr[i]);
        }

        bookService.edit(id, newBook, categoriesInt);

        showAllBook(request,response);


    }

    private void Create(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int status_id = Integer.parseInt(request.getParameter("status"));
        int nxb_id = Integer.parseInt(request.getParameter("nxb"));
        String urlOfImage = request.getParameter("urlOfImage");

        StatusBook status = new StatusBook(status_id);
        NXB nxb = new NXB(nxb_id);


        Book newBook = new Book(name,description,nxb,status, urlOfImage);


        String[] categoriesStr = request.getParameterValues("category");

        int[] categoriesInt= new int[categoriesStr.length];


        for (int i = 0; i < categoriesInt.length; i++) {
            categoriesInt[i]= Integer.parseInt(categoriesStr[i]);

        }

        bookService.create(newBook,categoriesInt);

        showAllBook(request, response);

    }
}
