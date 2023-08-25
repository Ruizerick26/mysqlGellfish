package com.example.demo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    static final String db_URL="jdbc:mysql://localhost/POO3";
    static final String usser="root";
    static final String pass="root_bas3";

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        //IMPRESION
        try{
           Connection conn = DriverManager.getConnection(db_URL,usser,pass);
           Statement stat = conn.createStatement();
           ResultSet rc = stat.executeQuery("Select * from REGISTROS");
           while(rc.next()){
               out.println("<h1>" + message + "</h1>");
               out.println("<p>"+ rc.getString("nombre")+"</p>");
               out.println("<p>"+ rc.getString("cedula")+"</p>");
               out.println("<p>"+ rc.getString("fecha")+"</p>");
               out.println("<p>"+ rc.getString("signo")+"</p>");
           }
            out.println("</body></html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void destroy() {
    }
}