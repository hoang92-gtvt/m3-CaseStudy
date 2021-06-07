package controller;

import model.*;
import service.book.BookService;
import service.book.IBookService;
import service.phieumuon.IPhieumuonService;
import service.phieumuon.PhieuMuonService;
import service.statusPM.IStatusPMService;
import service.statusPM.StatusPMService;
import service.user.IUserService;
import service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ControllerPhieuMuon", value = "/phieumuon")
public class ControllerPhieuMuon extends HttpServlet {

    IPhieumuonService phieumuonService = new PhieuMuonService();

    IUserService userService = new UserService();
    IStatusPMService statusPMService = new StatusPMService();
    IBookService bookService = new BookService();

    static User user = ControllerUser.user;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user=ControllerUser.user;
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
                    showPhieuMuonOfCustomer(request,response);
                    break;

                default:
                    if(user.getRole()==null){
                        response.sendRedirect("index.jsp");

                    } else if(user.getRole().getId()==1 || user.getRole().getId()==2 ){
                        showAllPhieuMuon(request, response);
                    } else response.sendRedirect("/phieumuon?action=customer");

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPhieuMuonOfCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        int idOfCustomer = user.getId();

        ArrayList<PhieuMuon> pmList = new ArrayList<>();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/list_customer.jsp");

        pmList = phieumuonService.getPhieuMuonListByIdOfCustomer(idOfCustomer);

        request.setAttribute("pmList", pmList);

        dispatcher.forward(request, response);

    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("phieumuons/delete.jsp");
        dispatcher.forward(request,response);

    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        PhieuMuon phieuMuon = phieumuonService.getObjectById(id);

        ArrayList<User> users = userService.findAll();
        ArrayList<StatusPM> statusPMList = statusPMService.findAll();
        ArrayList<Book> books = bookService.findAll();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/formEdit.jsp");
        request.setAttribute("old", phieuMuon);
        request.setAttribute("userList", users);
        request.setAttribute("statusPMList", statusPMList);
        request.setAttribute("bookList", books);


        dispatcher.forward(request,response);

    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        ArrayList<User> users = userService.findAll();
//        ArrayList<StatusPM> statusPMList = statusPMService.findAll();
        ArrayList<Book> books = bookService.findAll();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/formCreate.jsp");
        request.setAttribute("userList", users);
//        request.setAttribute("statusPMList", statusPMList);
        request.setAttribute("bookList", books);


        dispatcher.forward(request,response);

    }

    private void showAllPhieuMuon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<PhieuMuon> pmList = new ArrayList<>();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/list.jsp");

        pmList = phieumuonService.findAll();

        request.setAttribute("pmList", pmList);

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

                default:
                    showAllPhieuMuon(request, response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        phieumuonService.delete(id);
        showAllPhieuMuon(request, response);

    }

    private void Edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String identity = request.getParameter("identity");
        String date= request.getParameter("date");
        String duedate= request.getParameter("duedate");

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int statusPM_id = Integer.parseInt(request.getParameter("statusPM_id"));


        User user = userService.getObjectById(user_id);
        StatusPM statusPM = statusPMService.getObjectById(statusPM_id);

        PhieuMuon phieuMuon = new PhieuMuon(identity,date,duedate, user, statusPM);

        String[] book_id = request.getParameterValues("books");

        int[] book_int = new int[book_id.length];
        for (int i = 0; i < book_int.length; i++) {
            book_int [i]= Integer.parseInt(book_id[i]);

        }
        phieumuonService.edit(id,phieuMuon, book_int);

        showAllPhieuMuon(request, response);


    }

    private void Create(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String identity = request.getParameter("identity");
        String date= request.getParameter("date");
        String duedate= request.getParameter("duedate");

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int statusPM_id = Integer.parseInt(request.getParameter("statusPM_id"));


        User user = userService.getObjectById(user_id);
        StatusPM statusPM = statusPMService.getObjectById(statusPM_id);

        PhieuMuon phieuMuon = new PhieuMuon(identity,date,duedate, user, statusPM);

        String[] book_id = request.getParameterValues("books");

        int[] book_int = new int[book_id.length];
        for (int i = 0; i < book_int.length; i++) {
            book_int [i]= Integer.parseInt(book_id[i]);

        }

        phieumuonService.create(phieuMuon, book_int);

        showAllPhieuMuon(request, response);

    }
}
