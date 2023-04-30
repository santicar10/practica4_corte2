package com.example.practica4_corte2.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> getlist() throws SQLException;
    T getbyId(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(Long id,T o) throws SQLException;
}
