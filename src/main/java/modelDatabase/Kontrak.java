/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDatabase;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fas
 */
@Entity
@Table(name = "kontrak")
public class Kontrak implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 25)
    private String id;
    
    @Column(name = "id_kontrak", unique = true, nullable = false, length = 25)
    private String id_kontrak;
    
    @Column(name = "id_karyawan", unique = true, nullable = false, length = 25)
    private String id_karyawan;
    
    @Column(name = "tanggal_mulai", unique = false, nullable = false)
    private Date tanggal_mulai;
    
    @Column(name = "tanggal_berakhir", unique = false, nullable = false)
    private Date tanggal_berakhir;
    
    @Column(name = "gp_awal", unique = false, nullable = false)
    private Double gp_awal;
    
    @ManyToOne
    @JoinColumn(name = "id_karyawan", insertable = false, updatable = false)
    private Karyawan karyawan;

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public String getId_kontrak() {
        return id_kontrak;
    }

    public void setId_kontrak(String id_kontrak) {
        this.id_kontrak = id_kontrak;
    }

    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public Date getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_berakhir() {
        return tanggal_berakhir;
    }

    public void setTanggal_berakhir(Date tanggal_berakhir) {
        this.tanggal_berakhir = tanggal_berakhir;
    }

    public Double getGp_awal() {
        return gp_awal;
    }

    public void setGp_awal(Double gp_awal) {
        this.gp_awal = gp_awal;
    }
}
