/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icp.icp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelDatabase.Departemen;
import modelDatabase.hibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author fas
 */
public class PenggajianController {
 
    @RequestMapping(value="penggajian/input", method = RequestMethod.GET)
    public String loadAll(ModelMap model)
    {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Departemen.class);
        List<Departemen> departemens = criteria.list();
        //List<penjualan> lData = criteria.list();
        List dataShow = new ArrayList();
        for(Departemen departemen : departemens)
        {
            Map<String, String> result = new HashMap<String, String>();
            result.put("nama_departemen", departemen.getNama_departemen());
            result.put("id_departemen", departemen.getId_departemen());
            dataShow.add(result);
        }
        model.addAttribute("departemens", dataShow);
        //model.addAttribute("dataList", dataShow);
        session.close();
        return "penggajian";
    }
}
