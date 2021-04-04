package com.project1.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.ReimbursementDaoHibernate;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.ReimbursementService;
import com.project1.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UController {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService us = new UserService();

    public UController() {
    }

    public UController(UserService us) {
        this.us = us;
    }

    public void getAlL(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        System.out.println(jsonString);
        String json = objectMapper.writeValueAsString(us.fetchAllUsers());
        resp.getWriter().append(json);
        resp.setContentType("application/json");

    }

    public void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        User user = objectMapper.readValue(jsonString, User.class);
        int userid = user.getUserid();
        //System.out.println(userid);

        String json = objectMapper.writeValueAsString(us.getUserById(userid));
        resp.getWriter().append(json);
        resp.setContentType("application/json");
    }

    public void getByUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException{
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

        String json = objectMapper.writeValueAsString(us.getUserByUsername(username));
        resp.getWriter().append(json);
        resp.setContentType("application/json");



    }

}
