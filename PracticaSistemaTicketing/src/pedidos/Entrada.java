package pedidos;
import modelo.Asiento;

public class Entrada {

    private static int contadorEntradas = 1;
    
    private String idEntrada;
    private String idEvento;
    private String idSesion;

    private Asiento asiento;
    private double precioFinal;

    public Entrada(String idEvento, String idSesion, Asiento asiento, double precioFinal){
        this.idEntrada = String.format("ENT-%03d", contadorEntradas); // %03d para que sea ENT-001
        contadorEntradas++;
        this.idSesion=idSesion;
        this.asiento=asiento;
        this.precioFinal=precioFinal;
    }

    @Override
    public String toString() {
        String infoAsiento;
        
        if (this.asiento != null) { //si hay asiento asignado, si no lo es sería aforo geneal
            infoAsiento = this.asiento.getIdAsiento(); 
        } else {
            infoAsiento = "Aforo General";
        }
        return "TICKET [" + idEntrada + "] | Evento: " + idEvento + 
               " | Sesión: " + idSesion + 
               " | Asiento: " + infoAsiento + 
               " | Precio Final: " + precioFinal + "€";
    }
    // GETTERS Y SETTERS

    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
}
