/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    
    @Column(name = "id_departemen", unique = true, nullable = false, length = 25)
    private String id_departemen;
    
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

    public List<Kontrak> getKontrak() {
        return kontrak;
    }

    public void setKontrak(List<Kontrak> kontrak) {
        this.kontrak = kontrak;
    }
    
    
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

    public String getId_departemen() {
        return id_departemen;
    }

    public void setId_departemen(String id_departemen) {
        this.id_departemen = id_departemen;
    }

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
        int i = karyawan.getKontrak().size() - 1;
        if(i >= 0)
        {
            final Date kontrak_mulai = karyawan.getKontrak().get(i).
                    getTanggal_mulai();
            final Date kontrak_berakhir = karyawan.getKontrak().get(i).
                    getTanggal_berakhir();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY"); // Set your date format
            
            result.put("kontrak_mulai", sdf.format(kontrak_mulai));
            result.put("kontrak_berakhir", sdf.format(kontrak_berakhir));
        }
        else
        {
            result.put("kontrak_mulai", "12/08/211");
            result.put("kontrak_berakhir", "12/09/10");
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
    
}
