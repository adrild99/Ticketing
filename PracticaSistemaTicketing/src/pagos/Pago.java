package pagos;


import java.time.LocalDateTime;

public abstract class Pago implements Pagable {

    private String idPago;
    private LocalDateTime fecha;

    public Pago(String idPago) {
        this.idPago = idPago;
        this.fecha = LocalDateTime.now();
    }

    public abstract boolean procesarPago(double importe);

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
