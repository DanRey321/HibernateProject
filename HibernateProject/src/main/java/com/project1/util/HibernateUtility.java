package com.project1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateUtility {
        INSTANCE;

        private SessionFactory instance;

        public SessionFactory getSessionFactoryInstance(){
            if(instance == null){
                instance = new Configuration().configure().buildSessionFactory();
            }
            return instance;
        }
}
