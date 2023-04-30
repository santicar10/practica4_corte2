package com.example.practica4_corte2.utilities;

import com.example.practica4_corte2.anotations.MySqlConn;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionBD {
    @Inject
    private Logger log;
    @Resource(name="jdbc/mysqlDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    @MySqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    public void close(@Disposes @MySqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("cerrando la conexion a la bbdd mysql!");
    }
}