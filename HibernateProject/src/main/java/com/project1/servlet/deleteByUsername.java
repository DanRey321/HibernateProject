package com.project1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet("/user/delete")
public class deleteByUsername extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService = new UserService();

    public deleteByUsername() {
        super();
    }

    public deleteByUsername(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
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
            User user = objectMapper.readValue(jsonString, User.class);
            String username = user.getUsername();

            if(userService.deleteUser(username)){
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
