package controller;

import model.Book;
import model.NXB;
import model.User;
import service.book.BookService;
import service.book.IBookService;
import service.nxb.INXBService;
import service.nxb.NXBService;
import service.user.IUserService;
import service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ControllerUser", value = "/user")
public class ControllerUser extends HttpServlet {
     IUserService userService = new UserService();
     IBookService bookService = new BookService();
     INXBService nxbService = new NXBService();

     public static int id = 0;
     public static String name = "";
     public static String role_name = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch(action){
                case "admin":
                    showRequestAdmin(request,response);
                    break;
                case "customer":
                    showRequestCustomer(request,response);
                    break;
                default:
                    backHome(request,response);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null){
//            action = "";
//        }
        String userName = request.getParameter("userName");
        String pass = request.getParameter("pass");


        try {
            List<User> userList = userService.findAll();
            for (User user:userList ) {
                if(user.getUserName().equals(userName)){
                    if(user.getPass().equals(pass)){
                        id = user.getId();
                        name = user.getName();
                        role_name = user.getRole().getName();

                        if(user.getRole().getId()==1||user.getRole().getId()==2){
                            showRequestAdmin(request,response);
                        }else if(user.getRole().getId()==3){
                            showRequestCustomer(request,response);
                        }
                        return;
                    }

                }

            }
            backHome(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void backHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

        dispatcher.forward(request,response);
    }

    private void showRequestCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/customers.jsp");

        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("role", role_name);


        ArrayList<Book> bookList = bookService.findAll();
        ArrayList<NXB> nxbList = nxbService.findAll();
        request.setAttribute("bookList", bookList);
        request.setAttribute("nxbList", nxbList);


        dispatcher.forward(request,response);
    }

    private void showRequestAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin.jsp");

        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("role", role_name);


        ArrayList<Book> bookList = bookService.findAll();
        ArrayList<NXB> nxbList = nxbService.findAll();
        request.setAttribute("bookList", bookList);
        request.setAttribute("nxbList", nxbList);


        dispatcher.forward(request,response);

    }



}
