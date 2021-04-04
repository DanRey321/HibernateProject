package com.project1.servlet;
import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;
import com.project1.service.ReimbursementService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rServlet")
public class RiembursementServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReimbursementService rService = new ReimbursementService();

    public RiembursementServlet() {
        super();
    }

    public RiembursementServlet(ObjectMapper objectMapper, ReimbursementService rService) {
        this.objectMapper = objectMapper;
        this.rService = rService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{


        String json = objectMapper.writeValueAsString(rService.fetchAllReimbursements());
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

       //
        //
        // User user = null;

        rService.createReimbursement(jsonString);


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        if(rService.delete(objectMapper.readValue(jsonString, Reimbursement.class))){
            resp.getWriter().append(" Deleted from database!!!");
            resp.setStatus(200);
        }
        else{
            resp.getWriter().append(" Delete Employee failed");
            resp.setStatus(400);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonString = sb
                .toString();

        Reimbursement reimbursement = objectMapper.readValue(jsonString, Reimbursement.class);
        rService.updateReimbursements(reimbursement);
    }




}
