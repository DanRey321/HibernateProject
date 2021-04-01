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

@WebServlet("/username")
public class getUserByUsernameServlet  extends HttpServlet{
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService = new UserService();

    public getUserByUsernameServlet() {
        super();
    }

    public getUserByUsernameServlet(ObjectMapper objectMapper, UserService userService) {
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

        User user = objectMapper.readValue(jsonString, User.class);
        String username = user.getUsername();
        System.out.println(username);

        String json = objectMapper.writeValueAsString(userService.getUserByUsername(username));
        resp.getWriter().append(json);
        resp.setContentType("application/json");

    }
}
