/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDatabase;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Wandy
 */
@Entity
@Table(name = "departemen")
public class Departemen implements Serializable{
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;
    
    @Column(name = "id_departemen", unique = true, nullable = false, length = 10)
    private String id_departemen;
    
    @Column(name = "nama_departemen", nullable = false, length = 25)
    private String nama_departemen;
    
    public Departemen()
    {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_departemen() {
        return id_departemen;
    }

    public void setId_departemen(String id_departemen) {
        this.id_departemen = id_departemen;
    }

    public String getNama_departemen() {
        return nama_departemen;
    }

    public void setNama_departemen(String nama_departemen) {
        this.nama_departemen = nama_departemen;
    }
    
    
    
}
