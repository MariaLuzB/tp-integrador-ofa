package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.AutorService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mdominguez
 */
@SessionScoped
@Named("autorController")
public class AutorController implements Serializable{
    
    @Inject 
    private AutorService servicioAutores;
    
    private Autor autorSeleccionado;
    private  List<Autor> listaAutores;

    @PostConstruct
    public void init(){
        this.autorSeleccionado = null;
        this.listaAutores = servicioAutores.listar();
    }
    
    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(Autor autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }
    
    public String guardar(){
        Autor tmp = this.servicioAutores.guardar(autorSeleccionado);
        this.listaAutores.add(tmp);
        this.autorSeleccionado = null;
        return null;
    }

    public String nuevo(){
        this.autorSeleccionado = new Autor();
        return null;
    }    
   
}
