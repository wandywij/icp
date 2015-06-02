/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.ModelMap;

/**
 *
 * @author fas
 */
@Entity
@Table(name="karyawan")
public class Karyawan implements Serializable{

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;

    @Id
    @Column(name = "id_karyawan", unique = true, nullable = false, length = 25)
    private String id_karyawan;
    
//    @Column(name = "id_departemen", unique = true, nullable = false, length = 25)
//    private String id_departemen;
    
    @Column(name = "nama", unique = false, nullable = false, length = 100)
    private String nama;
    
    @Column(name = "alamat", unique = false, nullable = true, length = 100)
    private String alamat;
    
    @Column(name = "tempat_lahir", unique = false, nullable = true, length = 50)
    private String tempat_lahir;
    
    @Column(name = "tanggal_lahir", unique = false, nullable = true)
    private Date tanggal_lahir;
    
    @Column(name = "no_ktp", unique = true, nullable = false, length = 25)
    private String no_ktp;
 
    @Column(name = "fingerprint", unique = false, nullable = false, length = 25)
    private String fingerprint;
    
    @Column(name = "keterangan", unique = false, nullable = true, length = 500)
    private String keterangan;
    
    @OneToMany(mappedBy = "id_karyawan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //@OneToMany(mappedBy = "id_karyawan")
    private List<Kontrak> kontrak;

    @ManyToOne
    @JoinColumn(name = "id_departemen")
    private Departemen departemen;

    public Karyawan() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

//    public String getId_departemen() {
//        return id_departemen;
//    }
//
//    public void setId_departemen(String id_departemen) {
//        this.id_departemen = id_departemen;
//    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public Departemen getDepartemen() {
        return departemen;
    }

    public void setDepartemen(Departemen departemen) {
        this.departemen = departemen;
    }
    
    public List<Kontrak> getKontrak() {
        /*Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Karyawan.class);
        criteria.createAlias("kontrak", "kontrak");
        criteria.addOrder(Order.desc("kontrak.tanggal_berakhir"));
        criteria.setFirstResult(0).setMaxResults(1);
        List<Karyawan> temp = criteria.list();
        kontrak = temp.get(0).getKontrak();*/
        
//        List dataKontrak = new ArrayList();
//        for(Kontrak kon : kontrak) {
//            Map<String, String> result = kon.getKontrak(kon);
//            dataKontrak.add(result);
//        }
//        Collections.sort(dataKontrak, new Comparator<Map>() {
//
//            @Override
//            public int compare(Map k1, Map k2) {
//                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//                Date b1, b2;
//                try {
//                    b1 = format.parse(k1.get("tanggal_berakhir").toString());
//                } catch (ParseException pe) {
//                    pe.printStackTrace();
//                    b1 = new Date();
//                }
//                try {
//                    b2 = format.parse(k2.get("tanggal_berakhir").toString());
//                } catch (ParseException pe) {
//                    pe.printStackTrace();
//                    b2 = new Date();
//                }
//                if (b1.after(b2)) return -1;
//                if (b1.equals(b2)) return 0;
//                return 1;
//            }
//        });
        
        return kontrak;
    }

    public void setKontrak(List<Kontrak> kontrak) {
        this.kontrak = kontrak;
    }
    
//    private Kontrak kontrak;
//    public void setKontrak(Kontrak kontrak) {
//        this.kontrak = kontrak;
//    }
//    
//    public Kontrak getKontrak() {
//        return kontrak;
//    }
    
    public Map<String, String> getKaryawan(Karyawan karyawan)
    {
        Map<String, String> result = new HashMap<String, String>();
        result.put("nama_karyawan", karyawan.getNama());
        result.put("alamat", karyawan.getAlamat());
        result.put("tempat_lahir", karyawan.getTempat_lahir());
        SimpleDateFormat bdate = new SimpleDateFormat("dd MMMM YYYY");
        result.put("tgl_lahir", bdate.format(karyawan.getTanggal_lahir()));
        result.put("no_ktp", karyawan.getNo_ktp());
        result.put("bagian", karyawan.getDepartemen().getNama_departemen());
        result.put("keterangan", karyawan.getKeterangan());
        result.put("jumlah_kontrak", String.valueOf(karyawan.getKontrak().size()));
        int i = karyawan.getKontrak().size() - 1;
        long total_hari = 0;
        if(i >= 0)
        {           
           final Date kontrak_mulai = karyawan.getKontrak().get(i).
                   getTanggal_mulai();
           final Date kontrak_berakhir = karyawan.getKontrak().get(i).
                   getTanggal_berakhir();
           Double gp_awal = karyawan.getKontrak().get(i).getGp_awal();
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY"); // Set your date format
           long diff = 0, sisa = 0;
           Date now = new Date();
           for (int j=0; j<=i; j++) {
               Date km = karyawan.getKontrak().get(j).getTanggal_mulai();
               Date ka = karyawan.getKontrak().get(j).getTanggal_berakhir();
               diff = (ka.getTime() - km.getTime())/(1000*60*60*24);
               sisa = (ka.getTime() - now.getTime())/(1000*60*60*24);
               total_hari += diff;
           }
           
           int warning_type;
           if(kontrak_berakhir.getTime() <= now.getTime()) {
               warning_type = 2;
           } else if(kontrak_berakhir.getTime() - (14*24*60*60*1000) <= now.getTime()) {
               warning_type = 1;
           } else {
               warning_type = 0;
           }
           result.put("warning_type", String.valueOf(warning_type));
           result.put("kontrak_mulai", sdf.format(kontrak_mulai));
           result.put("kontrak_berakhir", sdf.format(kontrak_berakhir));
           DecimalFormat df = new DecimalFormat("#");
           df.setMaximumFractionDigits(0);
           result.put("gp_awal", df.format(gp_awal));
           result.put("lama_kontrak", String.valueOf(diff));
           result.put("sisa_kontrak", String.valueOf(sisa));
           result.put("total_hari", String.valueOf(total_hari));
        }
        else
        {
            result.put("warning_type", "0");
            result.put("kontrak_mulai", "12/08/211");
            result.put("kontrak_berakhir", "12/09/10");
            result.put("lama_kontrak", "0");
            result.put("sisa_kontrak", "0");
            result.put("total_hari", "0");
        }
        return result;
    }
    
    public List getAllKaryawan(List<Karyawan> karyawans)
    {
        ModelMap model = new ModelMap();
        List dataShow = new ArrayList();
        for(Karyawan karyawan : karyawans)
        {
            Map<String, String> result = getKaryawan(karyawan);
            dataShow.add(result);
        }
        
//        if(dataShow != null)
//        {
//            model.addAttribute("karyawans", dataShow);
//        }
//        return model;
        return dataShow;
    }
    
    public Criteria validate(Karyawan karyawan)
    {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Criteria karyawanCriteria = session.createCriteria(Karyawan.class);
        karyawanCriteria.add(Restrictions.eq("no_ktp", karyawan.getNo_ktp()));
        
        if(karyawanCriteria.uniqueResult() != null)
        {           
            return karyawanCriteria;
        }
        else
        {
            return null;
        }       
    }
    
}
