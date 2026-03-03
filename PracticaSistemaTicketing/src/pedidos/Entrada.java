package pedidos;
import modelo.Asiento;

public class Entrada {
    private String idEntrada;
    private String idEvento;
    private String idSesion;

    private Asiento asiento;
    private double precioFinal;

    public Entrada(String idEntrada, String idEvento, String idSesion, Asiento asiento, double precioFinal){
        this.idEntrada =idEntrada;
        this.idSesion=idSesion;
        this.asiento=asiento;
        this.precioFinal=precioFinal;
    }

    @Override
    public String toString() {
        String infoAsiento;
        
        if (this.asiento != null) { //Se comprueba si hay asiento asignado, si no lo es sería aforo geneal (else)
            infoAsiento = this.asiento.getIdAsiento(); 
        } else {
            infoAsiento = "Aforo General";
        }
        return "🎟️ TICKET [" + idEntrada + "] | Evento: " + idEvento + 
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
