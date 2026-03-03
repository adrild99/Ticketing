package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sesion {

    private static int contadorSesiones = 1;

    private String idSesion;
    private LocalDateTime fechaHora;
    private int aforoTotal;
    private int aforoDisponible;
    private ModoAforo modo; // es como Categoria en evento

    private ArrayList<Asiento> asientos = new ArrayList<>();

    public Sesion(LocalDateTime fechaHora, int aforoTotal, int aforoDisponible, ModoAforo modo) {
        this.idSesion = String.format("SES-%02d", contadorSesiones);
        contadorSesiones++;;
        
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
        // Sería igual que poner:
        // return this.aforoDisponible >= cantidad;
    }

    public void reservarGeneral(int cantidad) {
        this.aforoDisponible = this.aforoDisponible - cantidad; // quitamos la cantidad de entradas que se hayan
                                                                // comprado
    }

    public void liberarGeneral(int cantidad) {
        this.aforoDisponible = this.aforoDisponible + cantidad; // Devolvemos la cantidad de entradas que se devuelven
    }

    public ArrayList<Asiento> reservarAsientos(int cantidad) {

        if (this.aforoDisponible < cantidad) { //condicional para comprobar disponibilidad de asientos
            System.out.println("No hay suficientes asientos libres. Solo quedan " + this.aforoDisponible);
            return null; 
        }

        ArrayList<Asiento> asientosCarrito = new ArrayList<>();

        for (Asiento asiento : this.asientos) { //bucle para buscar los asientos (get) y reservarlos (set y add)
            if (asiento.getReservado() == false) {
                asiento.setReservado(true);
                asientosCarrito.add(asiento);

                if (asientosCarrito.size() == cantidad) {
                    break; 
                }
            }
        }

       
        this.aforoDisponible = this.aforoDisponible - cantidad; //Se actualiza el aforo y se devuelve la lista con el nuevo aforo disponible
        return asientosCarrito;
    }

    public void liberarAsientos(ArrayList<Asiento> asientosLiberar) {
        for (Asiento asiento : asientosLiberar) {// Recorremos los asientos que el cliente quiere devolver
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