package com.project1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.User;
import com.project1.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/login")
public class getByLoginServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService = new UserService();

    public getByLoginServlet(){
        super();
    }

    public getByLoginServlet (ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
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

        User user = objectMapper.readValue(jsonString, User.class);
        String username = user.getUsername();
        String pass = user.getPassword();

        System.out.println(username);
        System.out.println(pass);

        String json = objectMapper.writeValueAsString(userService.getUserByLogin(username,pass));
        resp.getWriter().append(json);
        resp.setContentType("application/json");

    }
}
