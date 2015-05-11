/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icp.icp;

import javax.servlet.http.HttpServletRequest;
import modelDatabase.Departemen;
import modelDatabase.hibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author fas
 */
@Controller
public class DepartemenController {
    private String prefix = "Dept";
    @RequestMapping(value="departemen/input", method = RequestMethod.GET)
    public String insert()
    {
        return "departemen";
    }
    
    @RequestMapping(value="departemen/input", method = RequestMethod.POST)
    public String save(ModelMap model, HttpServletRequest request )
    {
        final String nama_departemen = request.getParameter("nama_departemen");
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();
        
        Departemen departemen = new Departemen();
        departemen.setNama_departemen(nama_departemen);
        
        Criteria criteria = session.createCriteria(Departemen.class).
                setProjection(Projections.property("id"));
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(1);
        String kodedata = prefix + "0001";
        if (criteria.uniqueResult() != null ) {
            kodedata = String.valueOf(Integer.valueOf(criteria.uniqueResult().toString())+1);
            while (kodedata.length() < 4) {
                kodedata = "0"+kodedata;
            }
            kodedata = prefix + kodedata;
        }
        departemen.setId_departemen(kodedata);
        
        session.save(departemen);
        trx.commit();
        session.close();
               
        return "redirect:/departemen/input";
    }
}
