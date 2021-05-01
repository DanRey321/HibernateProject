package com.project1.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.ReimbursementDaoHibernate;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RController {

    //ReimbursementDaoHibernate rh;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReimbursementService rs = new ReimbursementService();

    public RController() {
    }

    public RController(ReimbursementService rs) {
        this.rs = rs;
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //System.out.println("Test");
        String json = objectMapper.writeValueAsString(rs.fetchAllReimbursements());
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

        Reimbursement r = objectMapper.readValue(jsonString, Reimbursement.class);
        //int authorid= r.getAuthor();
        int id = r.getId();
        System.out.println("Test ID");
        System.out.println("Use R id");
        String json = objectMapper.writeValueAsString(rs.getReimbursementsByID(id));
        resp.getWriter().append(json);
        resp.setContentType("application/json");
    }

    public void getByAuthor(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        Reimbursement r = objectMapper.readValue(jsonString, Reimbursement.class);
        int authorid= r.getAuthor();
        //int id = r.getId();

        System.out.println("Use author id");
        String json = objectMapper.writeValueAsString(rs.getReimbursementsByUserID(authorid));
        resp.getWriter().append(json);
        resp.setContentType("application/json");




    }


}
