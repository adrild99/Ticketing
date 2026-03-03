package modelo;

import java.util.ArrayList;

public abstract class Evento implements Vendible{
    
    private static int contadorEventos = 1;

    private String id;
    private String nombre;
    private String lugar;
    private Categoria categoria;
    private ArrayList<Sesion> sesiones = new ArrayList<>();


    public Evento(String nombre, String lugar, Categoria categoria) {
        this.id = String.format("EV-%02d", contadorEventos);
        contadorEventos++;
        this.nombre = nombre;
        this.lugar = lugar;
        this.categoria = categoria;
    }

    public void addSesion(Sesion s){
        this.sesiones.add(s);
    }
    public Sesion getSesionById(String id){
        for (Sesion s : this.sesiones) {
            if (s.getIdSesion().equals(id)) {
                return s; 
            }
        }
        return null;    
    }
    public abstract double getRecargoBase();

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " en " + lugar + " (" + categoria + ")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }
    
}
