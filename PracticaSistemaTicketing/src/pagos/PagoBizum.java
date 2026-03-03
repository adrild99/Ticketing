package pagos;


public class PagoBizum extends Pago {

    private String telefono;

    public PagoBizum(String idPago, String telefono) {
        super(idPago);
        this.telefono = telefono;
    }

    @Override
    public boolean procesarPago(double importe) {
        System.out.println("Conectando con Bizum");
        return true;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
