/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icp.icp;

import modelDatabase.Departemen;
import modelDatabase.hibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author fas
 */
public class Util { 
    
    public static Session getSession(Class classObject)
    {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(classObject);
        
        return session;
    }
}
