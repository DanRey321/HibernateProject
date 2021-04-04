package com.project1.dao;

import com.project1.model.Reimbursement;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

import com.project1.util.HibernateUtility;

public class ReimbursementDaoHibernate implements GenericDao<Reimbursement>{
    private static final Logger LOGGER = Logger.getLogger(ReimbursementDaoHibernate.class);

    @Override
    public List<Reimbursement> getList() {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            List<Reimbursement> result = session.createQuery("from Reimbursement", Reimbursement.class).list();
            if(result.isEmpty()){
                LOGGER.error("An attempt to get all reimbursements failed.");
                return null;
            }else{
                LOGGER.debug("All reimbursements were retrieved from the database.");
                return result;
            }
        }
    }

    @Override
    public Reimbursement getById(int id) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            Reimbursement r = session.get(Reimbursement.class, id);
            if(r==null){
                LOGGER.error("An attempt to get a reimbursement by ID" + id + " from the database was made, but it came empty");
                return null;
            }else{
                LOGGER.debug("A reimbursement by ID " + id + " was retrieved from the database.");
                return r;
            }
        }
    }

    @Override
    public List<Reimbursement> getByUserId(int id) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            List<Reimbursement> result = session.createQuery("from Reimbursement where author= :author",Reimbursement.class).setParameter("author", id).getResultList();
            if(result.isEmpty()){
                LOGGER.error("An attempt to get all reimbursements made by user ID " + id + " from the database was made, but it came empty");
                return null;
            }else{
                LOGGER.debug("A list of reimbursements made by user ID " + id + " was retrieved from the database.");
                return result;
            }
        }
    }

    @Override
    public Reimbursement getByUsername(String username) {
        //Empty. Reason - No use.
        return null;
    }

    @Override
    public Serializable insert(Reimbursement reimbursement) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            session.beginTransaction();
            Serializable res = session.save(reimbursement);
            session.flush();
            session.getTransaction().commit();
            if(res!=null) {
                LOGGER.debug("A new reimbursement was successfully added to the database.");
                return res;
            }else{
                LOGGER.error("An attempt to insert a reimbursement to the database failed.");
                return null;
            }
        }
    }

    @Override
    public void delete(Reimbursement reimbursement) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            session.beginTransaction();
            session.delete(reimbursement);
            session.getTransaction().commit();
            LOGGER.error("An attempt to delete a reimbursement from the database was made.");
        }
    }

    //@Override
    public void update(Reimbursement reimbursement){
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            Transaction tran = session.beginTransaction();
            session.update(reimbursement);
            tran.commit();
        }
    }
}
