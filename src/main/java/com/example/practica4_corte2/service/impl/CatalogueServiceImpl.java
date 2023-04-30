package com.example.practica4_corte2.service.impl;

import com.example.practica4_corte2.model.Categoria;
import com.example.practica4_corte2.model.Producto;
import com.example.practica4_corte2.repository.Repository;
import com.example.practica4_corte2.service.Service;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jdk.jfr.Category;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
public class CatalogueServiceImpl implements Service<Producto>, Serializable {
    @Inject
    private Repository<Producto> productRepository;
    @Inject
    private Repository<Categoria> categoryRepository;

    @Override
    public List<Producto> getlist() throws SQLException {
        return productRepository.list();

    }
    @Override
    public Producto getbyId(Long id) throws SQLException {
        return productRepository.byId(id);

    }

    @Override
    public void update(Long id, Producto o) throws SQLException {

    }

    @Override
    public void save(Producto producto) throws SQLException {
        productRepository.save(producto);


    }
    @Override
    public void delete(Long id) throws SQLException {
        productRepository.delete(id);

    }


    public List<Categoria> listCategory() throws SQLException {
        return categoryRepository.list();
    }

    public Categoria byCategoryId(Long id) throws SQLException {
        return categoryRepository.byId(id);
    }

    public void guardarCategoria(Categoria categoria) throws SQLException    {
        categoryRepository.save(categoria);
    }

    public void eliminarCategoria(Long id) throws SQLException {
        categoryRepository.delete(id);
    }

    public void guardarProductoConCategoria(Producto product, Categoria category) throws SQLException {
        categoryRepository.save(category);
        product.setCategory((Category) category);
        productRepository.save(product);
    }
}
