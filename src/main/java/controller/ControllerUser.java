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

     public static User user = new User();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "login";
        }
        try{
            switch(action){


                case "admin":
                    showRequestAdmin(request,response);
                    break;
                case "customer":
                    showRequestCustomer(request,response);
                    break;
                case "login":
                    backHome(request,response);
                    break;

                case "logout":
                    user =null;
                    backHome(request,response);
                    break;

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

    private void out(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null){
            action = "login";
        }
        try{
            switch(action){

//                case "admin":
//                    showRequestAdmin(request,response);
//                    break;
//                case "customer":
//                    showRequestCustomer(request,response);
//                    break;
                case "login":

                    login(request,response);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    private void login (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userName = request.getParameter("userName");
        String pass = request.getParameter("pass");

        List<User> userList = userService.findAll();
        for (User u:userList ) {
            if(u.getUserName().equals(userName)){
                if(u.getPass().equals(pass)){
                    id = u.getId();
                    name = u.getName();
                    role_name = u.getRole().getName();
                    user=u;

                    if(u.getRole().getId()==1||u.getRole().getId()==2){
                        showRequestAdmin(request,response);
                    }else if(u.getRole().getId()==3){
                        showRequestCustomer(request,response);
                    }
                    return;
                }

            }

        }
        backHome(request,response);

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

    public User getUserLogin(){

        return user;
    }




}
