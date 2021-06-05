package service.phieumuon;

import model.*;
import service.book.BookService;
import service.book.IBookService;
import service.connection.ConnectionJDBC;
import service.statusPM.IStatusPMService;
import service.statusPM.StatusPMService;
import service.user.IUserService;
import service.user.UserService;

import java.sql.*;
import java.util.ArrayList;

public class PhieuMuonService implements IPhieumuonService{
    public static final String insert_phieumuon = "insert into phieumuon(identity ,date, duedate, user_id, statusPM_id) value (?,?,?,?,?);";
    public static final String insert_DetailPM = "insert into detailpm(PM_id,book_id)value (?,?); ";
    public static final String Find_PHIEUMUON_BY_ID = "select * from phieumuon where id=?;";
    Connection connection = ConnectionJDBC.getConnect();

    IUserService userService = new UserService();
    IStatusPMService statusPMService =  new StatusPMService();

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
            StatusPM statusPM = statusPMService.getObjectById(status);
            ArrayList<Book> bookList = bookService.getBookListById(id);


            PhieuMuon phieuMuon = new PhieuMuon(id,identity,date,duedate,user,statusPM,bookList);
            pmList.add(phieuMuon);

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
        connection.setAutoCommit(false);

        PreparedStatement statement1 = connection.prepareStatement("delete from detailPM where PM_id= ?;");
        statement1.setInt(1,index);
        statement1.executeUpdate();

        PreparedStatement statement2 = connection.prepareStatement("delete from phieumuon where id= ?;");
        statement2.setInt(1,index);
        statement2.executeUpdate();

        connection.commit();

    }

    @Override
    public PhieuMuon getObjectById(int id) throws SQLException {
        PhieuMuon phieuMuon = null;
        PreparedStatement statement = connection.prepareStatement(Find_PHIEUMUON_BY_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){

            String  identity = result.getString("identity");
            String  date = result.getString("date");
            String  duedate = result.getString("duedate");

            phieuMuon = new PhieuMuon (id,identity,date,duedate);
        }

        return phieuMuon;
    }

    @Override
    public void create(PhieuMuon phieumuon, int[] book_id) throws SQLException {


        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement(insert_phieumuon, PreparedStatement.RETURN_GENERATED_KEYS );
        statement.setString(1, phieumuon.getIdentity());
        statement.setString(2, phieumuon.getDate());
        statement.setString(3, phieumuon.getDuedate());
        statement.setInt(4, phieumuon.getUser().getId());
        statement.setInt(5, phieumuon.getStatusPM().getId());

        statement.executeUpdate();
        int id=0;
        ResultSet result  = statement.getGeneratedKeys();
        while(result.next()){
            id = result.getInt(1);
        }

        PreparedStatement statement1 = connection.prepareStatement(insert_DetailPM);

        for (int i = 0; i < book_id.length; i++) {
            statement1.setInt(1, id);
            statement1.setInt(2, book_id[i]);
            statement1.executeUpdate();
        }

        connection.commit();


    }

    @Override
    public void edit(int id, PhieuMuon phieumuon, int[] book_id) throws SQLException {

        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement("update phieumuon set identity =?, date =?, duedate=?, user_id=?, statusPM_id=? where id=?;");
        statement.setString(1, phieumuon.getIdentity());
        statement.setString(2, phieumuon.getDate());
        statement.setString(3, phieumuon.getDuedate());
        statement.setInt(4, phieumuon.getUser().getId());
        statement.setInt(5, phieumuon.getStatusPM().getId());
        statement.setInt(6, id);
        statement.executeUpdate();


        PreparedStatement statement2 = connection.prepareStatement("delete from detailpm where PM_id=?");
        statement2.setInt(1,id);
        statement2.executeUpdate();

        PreparedStatement statement1 = connection.prepareStatement(insert_DetailPM );

        for (int i = 0; i < book_id.length; i++) {
            statement1.setInt(1, id);
            statement1.setInt(2, book_id[i]);
            statement1.executeUpdate();
        }

        connection.commit();

    }

    @Override
    public ArrayList<PhieuMuon> getPhieuMuonListByIdOfCustomer(int idOfCustomer) throws SQLException {
        ArrayList<PhieuMuon> pmList= new ArrayList<>();

        PreparedStatement statement =connection.prepareStatement("select *from phieumuon where user_id=? ;");
        statement.setInt(1,idOfCustomer);
        ResultSet result = statement.executeQuery();

        while(result.next()){
            int id = result.getInt(1);
            String identity = result.getString(2);
            String date = result.getString(3);
            String duedate= result.getString(4);

            int  user_id = result.getInt(5);
            int  status = result.getInt(6);

            User user = userService.getObjectById(user_id);
            StatusPM statusPM = statusPMService.getObjectById(status);
            ArrayList<Book> bookList = bookService.getBookListById(id);


            PhieuMuon phieuMuon = new PhieuMuon(id,identity,date,duedate,user,statusPM,bookList);
            pmList.add(phieuMuon);

        }

        return pmList;
    }
}
