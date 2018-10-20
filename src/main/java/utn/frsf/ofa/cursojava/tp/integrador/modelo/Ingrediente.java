/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author mdominguez
 */
@Entity
public class Ingrediente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;
    private String descripcion;
    private Double costo;

    // TODO Completar mapeo de relacion
    @ManyToMany
    (mappedBy = "ingredientes")
    private List<Receta> usadoEnRecetas;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public List<Receta> getUsadoEnRecetas() {
        return usadoEnRecetas;
    }

    public void setUsadoEnRecetas(List<Receta> usadoEnRecetas) {
        this.usadoEnRecetas = usadoEnRecetas;
    }

    @Override
    public String toString() {
        return id+";"+descripcion+";"+costo;
    }

    
    
}
