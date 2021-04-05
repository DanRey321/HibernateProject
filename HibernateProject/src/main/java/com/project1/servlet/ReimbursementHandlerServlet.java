package com.project1.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.Controller.RController;
import com.project1.model.Reimbursement;
import com.project1.model.updateList;
import com.project1.service.ReimbursementService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"*.R"})
public class ReimbursementHandlerServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReimbursementService rs = new ReimbursementService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        RController rController = new RController();
        System.out.println(req.getRequestURI());

        switch(req.getRequestURI()){
            case "/HibernateProject/byAuthor.R":
                rController.getByAuthor(req,resp);
                break;
            case "/HibernateProject/byId.R":
                rController.getById(req, resp);
                break;
            case "/HibernateProject/getAll.R" :
                rController.getAll(req, resp);
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


        rs.createReimbursement(jsonString);

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

        if(rs.delete(objectMapper.readValue(jsonString, Reimbursement.class))){
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



        updateList updateList = objectMapper.readValue(jsonString, updateList.class);
        int[][] array = updateList.getArray();
        int resolver = updateList.getResolver();
        int count = rs.updateReimbursements(array,resolver);
        resp.getWriter().append(" Columns Updated " + count);
        resp.setStatus(200);

    }

}
