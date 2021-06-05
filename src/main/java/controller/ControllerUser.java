package controller;

import model.User;
import service.user.IUserService;
import service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ControllerUser", value = "/user")
public class ControllerUser extends HttpServlet {
     IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null){
//            action = "";
//        }
        String userName = request.getParameter("userName");
        String pass = request.getParameter("pass");

        int id =0;
        String name = "";
        String role = "";
        try {
            List<User> userList = userService.findAll();
            for (User user:userList ) {
                if(user.getUserName().equals(userName)){
                    if(user.getPass().equals(pass)){
                        id = user.getId();
                        name = user.getName();
                        role = user.getRole().getName();
                        request.setAttribute("id", id);
                        request.setAttribute("name", name);
                        request.setAttribute("role", role);


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

    private void showRequestCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/customers.jsp");

        dispatcher.forward(request,response);
    }

    private void showRequestAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin.jsp");

        dispatcher.forward(request,response);

    }



}
