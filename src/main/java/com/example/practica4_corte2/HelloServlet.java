package com.example.practica4_corte2;

import java.io.*;
import java.sql.SQLException;

import com.example.practica4_corte2.service.impl.ProductServiceImpl;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    @Inject
    @Named("Service")
    private ProductServiceImpl service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(service);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            service.getlist().forEach(element->out.println(element.getProduct_name()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
