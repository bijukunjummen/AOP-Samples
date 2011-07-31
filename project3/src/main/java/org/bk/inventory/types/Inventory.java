package org.bk.inventory.types;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name="inventories")
public class Inventory implements Serializable{
    
    public Inventory(){
        //
    }
    
    public Inventory(String make, String model, String trim, String vin){
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.vin = vin;
    }
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    
    private String make;
    private String model;
    private String trim;
    private String vin;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getTrim() {
        return trim;
    }
    public void setTrim(String trim) {
        this.trim = trim;
    }
    @Override
    public String toString() {
        return "Inventory [id=" + id + ", vin=" + vin + ", make=" + make + ", model=" + model
                + ", trim=" + trim + "]";
    }
    
}
