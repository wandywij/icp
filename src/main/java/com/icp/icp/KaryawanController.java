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
        
        karyawan.setId_departemen(departemen);
        karyawan.setNama(nama_karyawan);
        karyawan.setAlamat(alamat);
        karyawan.setTempat_lahir(tempat_lahir);
        try {
            //String string = "January 2, 2010";
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            //Date date = format.parse(string);
            
            karyawan.setTanggal_lahir(format.parse(tanggal_lahir));
            kontrak.setTanggal_mulai(format.parse(kontrak_mulai));
            kontrak.setTanggal_berakhir(format.parse(kontrak_berakhir));
        } catch (ParseException pe) {
            karyawan.setTanggal_lahir(null);
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
