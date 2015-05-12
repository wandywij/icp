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
import javax.servlet.http.HttpServletRequest;
import modelDatabase.Karyawan;
import modelDatabase.Kontrak;
import modelDatabase.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    @RequestMapping(value="karyawan/input", method = RequestMethod.GET)
    public String insert()
    {
        return "karyawan";
    }
    
    @RequestMapping(value="karyawan", method = RequestMethod.GET)
    public String load()
    {
        return "daftar_karyawan";
    }
    
    @RequestMapping(value = "karyawan/input", method = RequestMethod.POST)
    public String save(ModelMap model, HttpServletRequest request) {
        final String nama_karyawan = request.getParameter("nama_karyawan");
        final String alamat = request.getParameter("alamat");
        final String tempat_lahir = request.getParameter("tempat_lahir");
        final String tanggal_lahir = request.getParameter("tanggal_lahir");
        final String no_ktp = request.getParameter("no_ktp");
//        final String departemen = request.getParameter("departemen");
        final String departemen = "DEPT0002";
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
        
        karyawan.setNama(nama_karyawan);
        karyawan.setAlamat(alamat);
        karyawan.setTempat_lahir(tempat_lahir);
        try {
            karyawan.setTanggal_lahir(new Timestamp(df.parse(tanggal_lahir).getTime()));
            kontrak.setTanggal_mulai(new Timestamp(df.parse(kontrak_mulai).getTime()));
            kontrak.setTanggal_berakhir(new Timestamp(df.parse(kontrak_berakhir).getTime()));
        } catch (ParseException pe) {
            karyawan.setTanggal_lahir(null);
        }
        karyawan.setNo_ktp(no_ktp);
        karyawan.setFingerprint(no_absen);
        karyawan.setKeterangan(keterangan);
        kontrak.setGp_awal(gp_awal);
        
        return "redirect:/karyawan/input";
    }
}
