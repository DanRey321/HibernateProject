package com.project1.service;

import java.util.List;
import com.project1.dao.ReimbursementDaoHibernate;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Reimbursement;

public class ReimbursementService {
	private ReimbursementDaoHibernate rh;
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);
	
	public ReimbursementService() {
		rh = new ReimbursementDaoHibernate();
	}
	
	public void createReimbursement(String json) {
		try {
			Reimbursement r = new ObjectMapper().readValue(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rh.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rh.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rh.getByUserId(id);
	}
	public Reimbursement getReimbursementsByID(int id) {
		return rh.getById(id);
	}

	public boolean delete(Reimbursement r){
		rh.delete(r);
		return rh.getById(r.getId())==null;
	}

	public boolean delete(int r){
		rh.delete(r);
		return rh.getById(r)==null;
	}


	public void updateReimbursements(int[][] i, int r) {
		rh.updateList(i, r);
	}

	public void updateReimbursements(Reimbursement r) {
		System.out.println(r.toString());
		rh.update(r);
	}
}
