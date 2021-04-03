package com.project1.dao;

import com.project1.model.Reimbursement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

import com.project1.util.HibernateUtility;

public class ReimbursementDaoHibernate implements GenericDao<Reimbursement>{
    @Override
    public List<Reimbursement> getList() {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){

            return session.createQuery("from Reimbursement", Reimbursement.class).list();
        }
        //return null;
    }

    @Override
    public Reimbursement getById(int id) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            return session.get(Reimbursement.class, id);
        }
        //return null;
    }

    @Override
    public List<Reimbursement> getByUserId(int id) {
        //return null;
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            return session.createQuery("from Reimbursement where author= :author",Reimbursement.class).setParameter("author", id).getResultList();
        }
    }

    @Override
    public Reimbursement getByUsername(String username) {
        return null;
    }

    @Override
    public Serializable insert(Reimbursement reimbursement) {
        try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
            Transaction tran = session.beginTransaction();
            session.persist(reimbursement);
            tran.commit();
        }

        return null;
    }

    @Override
    public void delete(Reimbursement reimbursement) {

    }
}
