package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sesion {

    private String idSesion;
    private LocalDateTime fechaHora;
    private int aforoTotal;
    private int aforoDisponible;
    private ModoAforo modo; // es como Categoria en evento

    private ArrayList<Asiento> asientos = new ArrayList<>();

    public Sesion(String idSesion, LocalDateTime fechaHora, int aforoTotal, int aforoDisponible, ModoAforo modo) {
        this.idSesion = idSesion;
        this.fechaHora = fechaHora;
        this.aforoTotal = aforoTotal;
        this.aforoDisponible = aforoDisponible;
        this.modo = modo;
    }

    public boolean hayDisponibilidad(int cantidad) {
        if (this.aforoDisponible >= cantidad) {
            return true; 
        } else {
            return false;
        }
        //Sería igual que poner:
        //return this.aforoDisponible >= cantidad;
    }

    public void reservarGeneral(int cantidad) {
        this.aforoDisponible = this.aforoDisponible - cantidad; //quitamos la cantidad de entradas que se hayan comprado
    }

    public void liberarGeneral(int cantidad) {
        this.aforoDisponible = this.aforoDisponible + cantidad; //Devolvemos la cantidad de entradas que se devuelven
    }

    public ArrayList<Asiento> reservarAsientos(int cantidad) {
        ArrayList<Asiento> asientosCarrito = new ArrayList<>();
        
        //recorre los asientos que hay en la sala
        for (Asiento asiento : this.asientos) {
            if (asiento.getReservado() == false) { 
                asiento.setReservado(true); 
                asientosCarrito.add(asiento); 
                
                if (asientosCarrito.size() == cantidad) {
                    break;
                }
            }
        }
        
        this.aforoDisponible = this.aforoDisponible - cantidad;
        return asientosCarrito;
    }

    public void liberarAsientos(ArrayList<Asiento> asientosLiberar) {
        // Recorremos los asientos que el cliente quiere devolver
        for (Asiento asiento : asientosLiberar) {
            asiento.setReservado(false); // Los volvemos a poner como libres
        }
        this.aforoDisponible = this.aforoDisponible + asientosLiberar.size();
    }

    public String getIdSesion() {
        return idSesion; 
    }
    public ModoAforo getModo() { 
        return modo; 
    }
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }
}