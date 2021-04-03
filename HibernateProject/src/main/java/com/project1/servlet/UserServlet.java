package com.project1.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.User;
import com.project1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user1")
public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService = new UserService();

    public UserServlet() {
        super();
    }

    public UserServlet(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws IOException{

        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        System.out.println(jsonString);

        //String json = objectMapper.writeValueAsString(userService.fetchAllUsers());
        String json = objectMapper.writeValueAsString(userService.fetchAllUsers());
        resp.getWriter().append(json);
        resp.setContentType("application/json");

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
            user = userService.insertUser(objectMapper.readValue(jsonString, User.class));
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
            if(userService.deleteUser(objectMapper.readValue(jsonString, User.class))){
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
