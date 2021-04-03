package com.project1.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.model.User;
import com.project1.service.ReimbursementService;
import com.project1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/byAuthorId")
public class RiembursementByIdServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReimbursementService rService = new ReimbursementService();

    public RiembursementByIdServlet() {
        super();
    }

    public RiembursementByIdServlet(ObjectMapper objectMapper, ReimbursementService rService) {
        this.objectMapper = objectMapper;
        this.rService = rService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
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
        int id = r.getId();


        if (authorid > 0){
            System.out.println("Use author id");
            String json = objectMapper.writeValueAsString(rService.getReimbursementsByUserID(authorid));
            resp.getWriter().append(json);
            resp.setContentType("application/json");
        }else if(id > 0){
            System.out.println("Use R id");
            String json = objectMapper.writeValueAsString(rService.getReimbursementsByID(id));
            resp.getWriter().append(json);
            resp.setContentType("application/json");

        }

    }

}
