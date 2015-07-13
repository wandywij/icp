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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modelDatabase.Departemen;
import modelDatabase.Karyawan;
import modelDatabase.Kontrak;
import modelDatabase.hibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Wandy
 */
@Controller
public class KaryawanController {

    private String prefix = "KAR";
    private String prefix_kontrak = "KTR";

    @RequestMapping(value = "karyawan/input", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Departemen.class);
        List<Departemen> departemens = criteria.list();
        //List<penjualan> lData = criteria.list();
        List dataShow = new ArrayList();
        for (Departemen departemen : departemens) {
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

    @RequestMapping(value = "karyawan/{kode}", method = RequestMethod.GET)
    public String loadPartial(ModelMap model, @PathVariable(value = "kode") String kode) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        final String sql
                = "SELECT karyawan.*, kontrak.tanggal_mulai, kontrak.tanggal_berakhir, kontrak.gp_awal, kontrak.id_kontrak "
                + "FROM karyawan, kontrak kontrak "
                + "where kontrak.id_kontrak = (SELECT kontrak.id_kontrak "
                + "FROM kontrak "
                + "WHERE kontrak.id_karyawan = karyawan.id_karyawan "
                + "ORDER BY kontrak.tanggal_berakhir DESC "
                + "LIMIT 1 "
                + ") and karyawan.no_ktp = '" + kode + "'";

        Query query = session.createSQLQuery(sql);

//        List<Karyawan> karyawans = query.list();
//        Karyawan karyawan = new Karyawan();
//        List dataShow = karyawan.getAllKaryawan(karyawans);
//        model.addAttribute("karyawans", dataShow);
        List<Karyawan> result = (List<Karyawan>) query.list();
        Iterator itr = result.iterator();
        List dataShow = new ArrayList();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            //now you have one array of Object for each row
//           String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
//           Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
            Karyawan karyawan = new Karyawan();
            karyawan.setId_karyawan(obj[1].toString());
            karyawan.setNama(obj[2].toString());
            karyawan.setAlamat(obj[3].toString());
            karyawan.setTempat_lahir(obj[4].toString());
            karyawan.setTanggal_lahir(new Date());
            karyawan.setNo_ktp(obj[6].toString());
            karyawan.setFingerprint(obj[7].toString());
            Departemen dpt = new Departemen();
            dpt.setId_departemen(obj[9].toString());
            karyawan.setKeterangan(obj[8].toString());
            karyawan.setDepartemen(dpt);
            Kontrak kont = new Kontrak();

            try {
                String tanggalMulai = obj[10].toString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date tanggalMulaiDate = format.parse(tanggalMulai);

                String tanggalBerakhir = obj[11].toString();
                Date tanggalBerakhirDate = format.parse(tanggalBerakhir);

                kont.setTanggal_mulai(tanggalMulaiDate);
                kont.setTanggal_berakhir(tanggalBerakhirDate);
                kont.setGp_awal(Double.parseDouble(obj[12].toString()));

                List<Kontrak> temp = new ArrayList<Kontrak>();
                temp.add(kont);
                karyawan.setKontrak(temp);
            } catch (ParseException ex) {
                Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Map<String, Object> result2 = karyawan.getKaryawan(karyawan);
            dataShow.add(result2);
            //same way for all obj[2], obj[3], obj[4]
        }

//        List<Karyawan> karyawans = (List<Karyawan>) query.list();
//        List dataShow = new ArrayList();
//        for (Karyawan karyawan : karyawans) {
//            Map<String, String> result = karyawan.getKaryawan(karyawan);
//
//            dataShow.add(result);
//        }
        Collections.sort(dataShow, new Comparator<Map>() {

            @Override
            public int compare(Map k1, Map k2) {
                long sisa1 = Long.parseLong(((String) k1.get("sisa_kontrak")));
                long sisa2 = Long.parseLong(((String) k2.get("sisa_kontrak")));
                if (sisa1 < sisa2) {
                    return -1;
                }
                if (sisa1 == sisa2) {
                    return 0;
                }
                return 1;
            }

//            @Override
//            public int compare(Karyawan k1, Karyawan k2) {
//                return (((Kontrak)k1.getKontrak()).getTanggal_berakhir()).compareTo(((Kontrak)k2.getKontrak()).getTanggal_berakhir());
//            }
        });
        model.addAttribute("karyawans", dataShow);
        session.close();
        return "daftar_karyawan_2";
    }

    @RequestMapping(value = {"karyawan", ""}, method = RequestMethod.GET)
    public String loadAll(ModelMap model, String kode) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        final String sql
                = "SELECT karyawan.*, kontrak.tanggal_mulai, kontrak.tanggal_berakhir, kontrak.gp_awal, kontrak.id_kontrak, "
                + "(SELECT COUNT(kontrak.id_karyawan) from kontrak WHERE kontrak.id_karyawan = karyawan.id_karyawan) as jumlah_kontrak, "
                + "(SELECT SUM(datediff(kontrak.tanggal_berakhir, kontrak.tanggal_mulai)) from kontrak where kontrak.id_karyawan = karyawan.id_karyawan) as total_lama_kontrak, "
                + "(SELECT departemen.nama_departemen from departemen WHERE id_departemen = karyawan.id_departemen) as bagian "
                + "FROM karyawan, kontrak kontrak "
                + "where kontrak.id_kontrak = (SELECT kontrak.id_kontrak "
                + "FROM kontrak "
                + "WHERE kontrak.id_karyawan = karyawan.id_karyawan "
                + "ORDER BY kontrak.tanggal_berakhir DESC "
                + "LIMIT 1 "
                + ")";

        Query query = session.createSQLQuery(sql);

//        List<Karyawan> karyawans = query.list();
//        Karyawan karyawan = new Karyawan();
//        List dataShow = karyawan.getAllKaryawan(karyawans);
//        model.addAttribute("karyawans", dataShow);
        List<Karyawan> result = (List<Karyawan>) query.list();
        Iterator itr = result.iterator();
        List dataShow = new ArrayList();
//        Date lastStart = null, lastEnd = null;
        List<Kontrak> tempList = new ArrayList<Kontrak>();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            //now you have one array of Object for each row
//           String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
//           Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int

            Karyawan karyawan = new Karyawan();
            karyawan.setId_karyawan(obj[1].toString());
            karyawan.setNama(obj[2].toString());
            karyawan.setAlamat(obj[3].toString());
            karyawan.setTempat_lahir(obj[4].toString());
            karyawan.setTanggal_lahir(new Date());
            karyawan.setNo_ktp(obj[6].toString());
            karyawan.setFingerprint(obj[7].toString());
            Departemen dpt = new Departemen();
            dpt.setId_departemen(obj[9].toString());
            dpt.setNama_departemen(obj[16].toString());
            karyawan.setKeterangan(obj[8].toString());
            karyawan.setDepartemen(dpt);
            karyawan.setJumlah_kontrak(Integer.parseInt(obj[14].toString()));
            karyawan.setTotal_lama_kontrak(Integer.parseInt(obj[15].toString()));
            try {
                String tanggalMulai = obj[10].toString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date tanggalMulaiDate = format.parse(tanggalMulai);

                String tanggalBerakhir = obj[11].toString();
                Date tanggalBerakhirDate = format.parse(tanggalBerakhir);

                Kontrak temp = new Kontrak();
                temp.setTanggal_mulai(tanggalMulaiDate);
                temp.setTanggal_berakhir(tanggalBerakhirDate);
                temp.setGp_awal(Double.parseDouble(obj[12].toString()));
                tempList.add(temp);
            } catch (ParseException ex) {
                Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
            }

            final String sqlKontrak = "SELECT * FROM kontrak WHERE id_karyawan='" + obj[1].toString() + "' "
                    + "ORDER BY tanggal_berakhir ASC";
            System.out.println("sql " + sqlKontrak);
            Query kontrakQuery = session.createSQLQuery(sqlKontrak);
            List<Kontrak> resultKontrak = (List<Kontrak>) kontrakQuery.list();
            Iterator itrKontrak = resultKontrak.iterator();
            List<Kontrak> detail = new ArrayList<Kontrak>();
            while (itrKontrak.hasNext()) {
                Object[] objKontrak = (Object[]) itrKontrak.next();
                Kontrak kont = new Kontrak();
                try {
                    String tanggalMulai = objKontrak[3].toString();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date tanggalMulaiDate = format.parse(tanggalMulai);

                    String tanggalBerakhir = objKontrak[4].toString();
                    Date tanggalBerakhirDate = format.parse(tanggalBerakhir);

                    kont.setTanggal_mulai(tanggalMulaiDate);
                    kont.setTanggal_berakhir(tanggalBerakhirDate);
                    kont.setGp_awal(Double.parseDouble(objKontrak[5].toString()));

                    detail.add(kont);
                } catch (ParseException ex) {
                    Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            karyawan.setKontrak(tempList);
            karyawan.setKontrakDetail(detail);

            Map<String, Object> result2 = karyawan.getKaryawan(karyawan);
            dataShow.add(result2);
            //same way for all obj[2], obj[3], obj[4]
        }

//        List<Karyawan> karyawans = (List<Karyawan>) query.list();
//        List dataShow = new ArrayList();
//        for (Karyawan karyawan : karyawans) {
//            Map<String, String> result = karyawan.getKaryawan(karyawan);
//
//            dataShow.add(result);
//        }
        Collections.sort(dataShow, new Comparator<Map>() {

            @Override
            public int compare(Map k1, Map k2) {
                long sisa1 = Long.parseLong(((String) k1.get("sisa_kontrak")));
                long sisa2 = Long.parseLong(((String) k2.get("sisa_kontrak")));
                if (sisa1 < sisa2) {
                    return -1;
                }
                if (sisa1 == sisa2) {
                    return 0;
                }
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

    @RequestMapping(value = "karyawan/input", produces = "applicatoin/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
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

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date temp;
            temp = format.parse(kontrak_mulai);
            final long tanggal_mulai_kontrak_baru = temp.getTime();
            temp = format.parse(kontrak_berakhir);
            final long tanggal_berakhir_kontrak_baru = temp.getTime();

            if (tanggal_mulai_kontrak_baru > tanggal_berakhir_kontrak_baru) {
                JSONObject jobj = new JSONObject();
                jobj.put("error", "error");
                jobj.put("message", "Maaf, tanggal kontrak berakhir harus lebih besar daripada tanggal kontrak mulai");
                return jobj.toString();

            } else {
                Session session = hibernateUtil.getSessionFactory().openSession();
                Transaction trx = session.beginTransaction();

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Karyawan karyawan = new Karyawan();
                Kontrak kontrak = new Kontrak();
                karyawan.setNama(nama_karyawan);
                karyawan.setAlamat(alamat);
                karyawan.setTempat_lahir(tempat_lahir);
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

                Criteria karyawanCriteria = karyawan.validate(karyawan);

                if (karyawanCriteria == null) //blm ada di database
                {
                    Criteria criteria = session.createCriteria(Karyawan.class).setProjection(Projections.property("id"));
                    criteria.addOrder(Order.desc("id"));
                    criteria.setMaxResults(1);
                    String kodedata = prefix + "0001";
                    if (criteria.uniqueResult() != null) {
                        System.out.println("criteria uniqueresult " + criteria.uniqueResult());
                //Karyawan temp = (Karyawan) criteria.uniqueResult();

                        //kodedata = String.valueOf(temp.getId() + 1);
                        kodedata = String.valueOf(Integer.valueOf(criteria.uniqueResult().toString()) + 1);
                        while (kodedata.length() < 4) {
                            kodedata = "0" + kodedata;
                        }
                        kodedata = prefix + kodedata;
                    }

                    Criteria departemenCriteria = session.createCriteria(Departemen.class);
                    departemenCriteria.add(Restrictions.eq("id_departemen", departemen));
                    //System.out.println("id_departemen " + departemen);
                    if (departemenCriteria.uniqueResult() != null) {
                        Departemen departemenTemp = new Departemen();
                        departemenTemp = (Departemen) departemenCriteria.uniqueResult();
                        karyawan.setDepartemen(departemenTemp);
                    }
                    karyawan.setId_karyawan(kodedata);
//
                    criteria = session.createCriteria(Kontrak.class).
                            setProjection(Projections.property("id"));
                    criteria.addOrder(Order.desc("id"));
                    criteria.setMaxResults(1);
                    String kodekontrak = prefix_kontrak + "0001";
                    if (criteria.uniqueResult() != null) {
                        kodekontrak = String.valueOf(Integer.valueOf(criteria.uniqueResult().toString()) + 1);
                        while (kodekontrak.length() < 4) {
                            kodekontrak = "0" + kodekontrak;
                        }
                        kodekontrak = prefix_kontrak + kodekontrak;
                    }

                    //String kodekontrak = returnKodeKontrak();
                    kontrak.setId_kontrak(kodekontrak);
                    kontrak.setKaryawan(karyawan);

                    session.save(karyawan);
                    session.save(kontrak);
                    trx.commit();
                    session.close();
                } else {
                    final Karyawan tempKaryawan = (Karyawan) karyawanCriteria.uniqueResult();
                    boolean isValid = karyawan.isValid(tempKaryawan,
                            kontrak_mulai);
                    System.out.println("isValid " + isValid);
                    if (isValid) {

                        Criteria criteria = session.createCriteria(Kontrak.class).
                                setProjection(Projections.property("id"));

                        //.add(Property.forName("tanggal_berakhir").eq(maxQuery));
                        criteria.addOrder(Order.desc("id"));
                        criteria.setMaxResults(1);
                        String kodekontrak = prefix_kontrak + "0001";
                        if (criteria.uniqueResult() != null) {
                            kodekontrak = String.valueOf(Integer.valueOf(criteria.uniqueResult().toString()) + 1);
                            while (kodekontrak.length() < 4) {
                                kodekontrak = "0" + kodekontrak;
                            }
                            kodekontrak = prefix_kontrak + kodekontrak;
                        }
                        kontrak.setId_kontrak(kodekontrak);
                        kontrak.setKaryawan(tempKaryawan);
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
                        kontrak.setGp_awal(gp_awal);
                        //session.update(tempKaryawan);
                        session.save(kontrak);
                        trx.commit();
                        session.close();

                        return "redirect:/karyawan";
                    } else {
                        JSONObject jobj = new JSONObject();
                        jobj.put("error", "error");
                        jobj.put("message", "Maaf, kontrak untuk karyawan ini masih berlaku");
                        return jobj.toString();
                    }
                    //return "isValid";
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/karyawan/input";
    }

    @RequestMapping(value = "karyawan/validasi", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    private String validate(ModelMap model, HttpServletRequest request) {
        String noKtp = request.getParameter("no_ktp");
        Karyawan karyawan = new Karyawan();
        boolean isValid = karyawan.isValid(noKtp);
        JSONObject jObj = new JSONObject();
        if (isValid) {
            jObj.put("is_valid", 1);
        } else {
            jObj.put("is_valid", 0);
        }
        return jObj.toString();
    }

    @RequestMapping(value = "karyawan.json", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String autocompletePegawai(HttpServletRequest request) {
        String q = request.getParameter("term");
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Karyawan.class);

        criteria.add(Restrictions.or(Restrictions.like("nama", "%" + q + "%").ignoreCase(), Restrictions.like("no_ktp", "%" + q + "%").ignoreCase()));
        JSONArray jsonArrayy = new JSONArray();
        try {
            List<Karyawan> lpegawai = criteria.list();
            for (Karyawan pg : lpegawai) {
                System.out.println("term " + pg.getNama());
                jsonArrayy.put(pg.getNama() + " / " + pg.getNo_ktp());
            }

        } catch (Exception ex) {

        }
        for (int i = 0; i < 5; i++) {
            jsonArrayy.put(i + "/ iseng");
        }
        session.close();
        return jsonArrayy.toString();
    }

//    
}
