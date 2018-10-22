package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.IngredienteService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mdominguez
 */

// TODO: definir anotacion de ambito 
@SessionScoped
@Named("recetaController")
public class RecetaController implements Serializable {

    @Inject
    RecetaService recetaSrv;

    @Inject
    IngredienteService ingredienteSrv;

    private Receta recetaSeleccionada;
    private Autor autorSeleccionado;
    private List<Receta> listaRecetas;

    private DualListModel<Ingrediente> ingredientesDisponibles;
    
    private Double busquedaPrecioMin;
    private Double busquedaPrecioMax;
    private Autor busquedaAutor;
    //private Ingrediente busquedaIngrediente;

    public void setBusquedaPrecioMin(Double precio){
    this.busquedaPrecioMin=precio;
    }
    
    public Double getBusquedaPrecioMin(){
        return busquedaPrecioMin;
    }
    
    public void setBusquedaPrecioMax(Double precio){
    this.busquedaPrecioMax=precio;
    }
    
    public Double getBusquedaPrecioMax(){
    return busquedaPrecioMax;
    }
    
    public void setBusquedaAutor(Autor autor){
    this.busquedaAutor=autor;
    }
    
    public Autor getBusquedaAutor(){
    return busquedaAutor;
    }
    
    /*public void setBusquedaIngrediente(Ingrediente ing){
    this.busquedaIngrediente=ing;
    }
    
    public Ingrediente getBusquedaIngrediente(){
    return busquedaIngrediente;
    }*/
    
    public void buscar() {        
        this.listaRecetas=this.recetaSrv.busquedaAvanzada(busquedaAutor, busquedaPrecioMin, busquedaPrecioMax);
         

    }

    
    public Receta getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(Receta recetaSeleccionada) {
        this.recetaSeleccionada = recetaSeleccionada;
        this.recetaSeleccionada .setIngredientes(recetaSrv.ingredientesPorIdReceta(recetaSeleccionada.getId()));
        this.ingredientesDisponibles.setTarget(recetaSeleccionada.getIngredientes());       
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @PostConstruct
    public void init() {
        this.recetaSeleccionada = null;
        this.listaRecetas = recetaSrv.listar();
        List<Ingrediente> origen = ingredienteSrv.listar();
        List<Ingrediente> destino = new ArrayList<Ingrediente>();
        this.ingredientesDisponibles = new DualListModel<>(origen, destino);        
    }

    public DualListModel<Ingrediente> getIngredientesDisponibles() {
        return ingredientesDisponibles;
    }

    public void setIngredientesDisponibles(DualListModel<Ingrediente> ingredientesDisponibles) {
        this.ingredientesDisponibles = ingredientesDisponibles;
    }

    public String guardar() {
        recetaSeleccionada.setIngredientes(this.ingredientesDisponibles.getTarget());
        // TODO completar el metodo guardar
        // setear el autor de la receta seleccionada
        recetaSeleccionada.setAutor(autorSeleccionado);
        // invocar al metodo qeu guarda la receta
        Receta receta = this.recetaSrv.guardar(recetaSeleccionada);
        this.listaRecetas.add(receta);        
        this.recetaSeleccionada = null;
        return null;
    }

    public String nuevo() {
        this.recetaSeleccionada = new Receta();
        this.recetaSeleccionada.setIngredientes(new ArrayList<>());
        this.ingredientesDisponibles.setTarget(new ArrayList<Ingrediente>());
        return null;
    }

    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(Autor autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    
}
