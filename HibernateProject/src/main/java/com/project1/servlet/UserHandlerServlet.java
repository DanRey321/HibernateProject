package com.project1.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.Controller.UController;
import com.project1.model.User;
import com.project1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"*.User"})
public class UserHandlerServlet extends HttpServlet{
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        UController uController = new UController();
        System.out.println(req.getRequestURI());

        switch(req.getRequestURI()){
            case "/HibernateProject/getAll.User":
                uController.getAlL(req,resp);
                break;
            case "/HibernateProject/byId.User":
                uController.getById(req, resp);
                break;
            case "/HibernateProject/byUsername.User" :
                System.out.println("True");
                uController.getByUsername(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        User user = null;

        //System.out.println(jsonString);
        try {
            user = us.insertUser(objectMapper.readValue(jsonString, User.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String insertedEmployeeJSON = objectMapper.writeValueAsString(user);
        resp.getWriter().append(insertedEmployeeJSON);

        resp.setContentType("application/json");
        resp.setStatus(201);
        if(user!=null)
            System.out.println(user.toString());


    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        try{
            if(us.deleteUser(objectMapper.readValue(jsonString, User.class))){
                resp.getWriter().append(" Deleted from database!!!");
                resp.setStatus(200);
            }else{
                resp.getWriter().append(" Delete user failed");
                resp.setStatus(400);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


    }



}
