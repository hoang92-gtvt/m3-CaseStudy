package service.phieumuon;

import model.PhieuMuon;
import service.IService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPhieumuonService extends IService<PhieuMuon> {

    public void create(PhieuMuon phieumuon, int[] book_id) throws SQLException;
    public void edit (int id, PhieuMuon phieumuon, int[] book_id) throws SQLException;
    public ArrayList<PhieuMuon> getPhieuMuonListByIdOfCustomer(int id)throws SQLException;
}
