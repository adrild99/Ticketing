package pagos;


public class PagoTarjeta extends Pago {

    private String numTarjeta;
    private String titular;

    public PagoTarjeta(String idPago, String numTarjeta, String titular) {
        super(idPago);
        this.numTarjeta = numTarjeta;
        this.titular = titular;
    }

    @Override
    public boolean procesarPago(double importe) {
        System.out.println("Cobrando " + importe + "€ a la tarjeta de " + titular);

        return true;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
