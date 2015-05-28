/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icp.icp;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modelDatabase.Departemen;
import modelDatabase.Karyawan;
import modelDatabase.Kontrak;
import modelDatabase.hibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wandy
 */
@Controller
public class KaryawanController {
    private String prefix = "KAR";
    private String prefix_kontrak = "KTR";
    
    @RequestMapping(value="karyawan/input", method = RequestMethod.GET)
    public String insert(ModelMap model)
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
        return "karyawan";
    }
    
    @RequestMapping(value={"karyawan", ""}, method = RequestMethod.GET)
    public String loadAll(ModelMap model)
    {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Karyawan.class);
        List<Karyawan> karyawans = criteria.list();
//        Karyawan karyawan = new Karyawan();
//        List dataShow = karyawan.getAllKaryawan(karyawans);
//        model.addAttribute("karyawans", dataShow);
        List dataShow = new ArrayList();
        for(Karyawan karyawan : karyawans)
        {
            Map<String, String> result = karyawan.getKaryawan(karyawan);
            
            dataShow.add(result);            
        }
        Collections.sort(dataShow, new Comparator<Map>() {

            @Override
            public int compare(Map k1, Map k2) {
                long sisa1 = Long.parseLong(((String)k1.get("sisa_kontrak")));
                long sisa2 = Long.parseLong(((String)k2.get("sisa_kontrak")));
                if (sisa1 < sisa2) return -1;
                if (sisa1 == sisa2) return 0;
                return 1;
            }

//            @Override
//            public int compare(Karyawan k1, Karyawan k2) {
//                return (((Kontrak)k1.getKontrak()).getTanggal_berakhir()).compareTo(((Kontrak)k2.getKontrak()).getTanggal_berakhir());
//            }
            
        });
        model.addAttribute("karyawans", dataShow);
        session.close();
        return "daftar_karyawan";
    }
    
    @RequestMapping(value = "karyawan/input", method = RequestMethod.POST)
    public String save(ModelMap model, HttpServletRequest request) {
        final String nama_karyawan = request.getParameter("nama_karyawan");
        final String alamat = request.getParameter("alamat");
        final String tempat_lahir = request.getParameter("tempat_lahir");
        final String tanggal_lahir = request.getParameter("tanggal_lahir");
        final String no_ktp = request.getParameter("no_ktp");
//        final String departemen = request.getParameter("bagian");
        final String departemen = request.getParameter("bagian");
        final String kontrak_mulai = request.getParameter("kontrak_mulai");
        final String kontrak_berakhir = request.getParameter("kontrak_berakhir");
        final double gp_awal = Double.parseDouble(request.getParameter("gp_awal"));
        final String no_absen = request.getParameter("no_absen");
        final String keterangan = request.getParameter("keterangan");
        
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Karyawan karyawan = new Karyawan();
        Kontrak kontrak = new Kontrak();
        
//        karyawan.setId_departemen(departemen);
        karyawan.setNama(nama_karyawan);
        karyawan.setAlamat(alamat);
        karyawan.setTempat_lahir(tempat_lahir);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            karyawan.setTanggal_lahir(format.parse(tanggal_lahir));
        } catch (ParseException pe) {
            pe.printStackTrace();
            karyawan.setTanggal_lahir(new Date());
        }
        try {
            kontrak.setTanggal_mulai(format.parse(kontrak_mulai));
        } catch (ParseException pe) {
            pe.printStackTrace();
            kontrak.setTanggal_mulai(new Date());
        }
        try {
            kontrak.setTanggal_berakhir(format.parse(kontrak_berakhir));
        } catch (ParseException pe) {
            pe.printStackTrace();
            kontrak.setTanggal_berakhir(new Date());
        }

        karyawan.setNo_ktp(no_ktp);
        karyawan.setFingerprint(no_absen);
        karyawan.setKeterangan(keterangan);
        kontrak.setGp_awal(gp_awal);
        
        Criteria criteria = session.createCriteria(Karyawan.class).
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
        
        Criteria deptCriteria = session.createCriteria(Departemen.class);
        deptCriteria.add(Restrictions.eq("id_departemen", departemen));
        System.out.println("id_departemen"+departemen);
        if (deptCriteria.uniqueResult() != null) {
            Departemen departemenTemp = new Departemen();
            departemenTemp = (Departemen) deptCriteria.uniqueResult();
            karyawan.setDepartemen(departemenTemp);
        } else {
            System.out.println("deptCriteria null ");
        }
        
        karyawan.setId_karyawan(kodedata);
        
        criteria = session.createCriteria(Kontrak.class).
                setProjection(Projections.property("id"));
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(1);
        String kodekontrak = prefix_kontrak + "0001";
        if (criteria.uniqueResult() != null ) {
            kodekontrak = String.valueOf(Integer.valueOf(criteria.uniqueResult().toString())+1);
            while (kodekontrak.length() < 4) {
                kodekontrak = "0"+kodekontrak;
            }
            kodekontrak = prefix_kontrak + kodekontrak;
        }
        kontrak.setId_kontrak(kodekontrak);
        kontrak.setKaryawan(karyawan);
        
        session.save(karyawan);
        session.save(kontrak);
        trx.commit();
        session.close();
        
        return "redirect:/karyawan/input";
    }
    
}
