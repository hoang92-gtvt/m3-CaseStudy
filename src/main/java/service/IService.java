package service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IService <E> {
    ArrayList<E> findAll() throws SQLException;
    void creat(E newE);
    void edit(int index, E newE);
    void delete(int index);
}