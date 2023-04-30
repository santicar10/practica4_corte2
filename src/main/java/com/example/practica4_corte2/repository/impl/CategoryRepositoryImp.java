package com.example.practica4_corte2.repository.impl;

import com.example.practica4_corte2.model.Categoria;
import com.example.practica4_corte2.repository.Repository;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryRepositoryImp {
    public static class CategoryRepositorylmp implements Repository<Categoria> {


        @Inject
        private Connection coon;

        public final Logger log = Logger.getLogger(CategoryRepositoryImp.class.getName());

        private Categoria createCategory(ResultSet resultSet) throws SQLException {
            Categoria category = new Categoria();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("category_name"));
            return category;
        }

        @Override
        public List<Categoria> list() {
            List<Categoria> categories = new ArrayList<>();
            try (Statement statement = coon.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM categories")
            ) {
                while (resultSet.next()) {
                    Categoria category = createCategory(resultSet);
                    categories.add(category);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return categories;
        }

        @Override
        public Categoria byId(Long id) {
            Categoria category = null;
            try (PreparedStatement preparedStatement = coon.prepareStatement("SELECT * FROM categories where id=?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    category = createCategory(resultSet);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return category;
        }

        @Override
        public Categoria save(Categoria o) {
            String sql = null;
            if (o.getId() != null && o.getId() > 0) {
                sql = "UPDATE categories SET category_name=? WHERE id=?";
            } else {
                sql = "INSERT INTO categories(category_name) VALUES(?)";
            }
            try (PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, o.getName());
                if (o.getId() != null && o.getId() > 0) {
                    stmt.setInt(2, o.getId());
                }
                stmt.executeUpdate();
                if (o.getId() == null) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            o.setId(rs.getInt(1));
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return o;

        }

        @Override
        public void delete(Long id) {
            Categoria category = null;
            try (PreparedStatement preparedStatement = coon.prepareStatement("DELETE FROM categories WHERE id =?")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void update(Long id, Categoria o) {
            Categoria categoria = (Categoria) o;
            try (PreparedStatement preparedStatement = coon.prepareStatement("UPDATE categories SET category_name=? where id=?")) {
                preparedStatement.setString(1, categoria.getName());
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


}
