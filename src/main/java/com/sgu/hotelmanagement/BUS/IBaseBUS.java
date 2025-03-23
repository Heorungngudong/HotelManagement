package com.sgu.hotelmanagement.BUS;

import java.util.ArrayList;

public interface IBaseBUS<T> {
    public boolean create(T object);
    public T getById(int id);
    public ArrayList<T> getAll();
    public boolean update(T object);
    public boolean delete(T object);
}
