package com.example.practica4_corte2.service.impl;

import com.example.practica4_corte2.model.Producto;
import com.example.practica4_corte2.repository.impl.ProductRepostoryImp;
import com.example.practica4_corte2.service.Service;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
public class ProductServiceImpl implements Service<Producto>, Serializable {

    @Inject
    private ProductRepostoryImp Repostory;

    @Override
    public List<Producto> getlist() throws SQLException {
        System.out.println("------------LIST PRODUCTS------------");

        return Repostory.list();
    }

    @Override
    public Producto getbyId(Long id) throws SQLException {
        System.out.println("------------GET PRODUCT BY ID------------");
        return Repostory.byId(id);
    }

    @Override
    public void save(Producto o) throws SQLException {
        System.out.println("------------SAVE PRODUCT------------");
        Repostory.save(o);
    }

    @Override
    public void delete(Long id) throws SQLException {
        System.out.println("------------DELETE PRODUCT------------");
        Repostory.delete(id);
    }

    @Override
    public void update(Long id,Producto o) throws SQLException {
        System.out.println("------------UPDATE PRODUCT------------");
        Repostory.update(id,o);
    }
}
