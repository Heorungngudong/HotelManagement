package com.sgu.hotelmanagement.DAO;

import java.util.ArrayList;

public interface IBaseDAO<T> {
    public boolean create(T obj);
    public boolean update(T obj);
    public boolean delete(T obj);
    public T getById(int id);
    public ArrayList<T> getAll();
}
