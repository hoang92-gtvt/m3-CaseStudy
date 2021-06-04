package service.phieumuon;

import model.*;
import service.book.BookService;
import service.book.IBookService;
import service.connection.ConnectionJDBC;
import service.statusPM.IStatusPMService;
import service.statusPM.StatusService;
import service.user.IUserService;
import service.user.UserService;

import java.sql.*;
import java.util.ArrayList;

public class PhieuMuonService implements IPhieumuonService{
    Connection connection = ConnectionJDBC.getConnect();

    IUserService userService = new UserService();
    IStatusPMService statusPMService =  new StatusService();

    IBookService bookService = new BookService();


    @Override
    public ArrayList<PhieuMuon> findAll() throws SQLException {
        ArrayList<PhieuMuon> pmList= new ArrayList<>();

        PreparedStatement statement =connection.prepareStatement("select *from phieumuon;");

        ResultSet result = statement.executeQuery();

        while(result.next()){
            int id = result.getInt(1);
            String identity = result.getString(2);
            String date = result.getString(3);
            String duedate= result.getString(4);

            int  user_id = result.getInt(5);
            int  status = result.getInt(6);

            User user = userService.getObjectById(user_id);
            StatusPM statusPM = statusPMService.getObjectById();


//
//            ArrayList<Category> categories = (ArrayList<Category>) categoryService.findCategoryByID(idBook);
//
//            Book book = new Book(idBook,nameBook,description ,nxbObject, statusBook, categories );

//            pmList.add(book);

        }



        return pmList;
    }

    @Override
    public void creat(PhieuMuon newE) {

    }

    @Override
    public void edit(int index, PhieuMuon newE) {

    }

    @Override
    public void delete(int index) throws SQLException {

    }

    @Override
    public PhieuMuon getObjectById(int id) throws SQLException {
        return null;
    }
}