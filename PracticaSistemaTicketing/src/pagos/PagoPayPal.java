package pagos;

public class PagoPayPal extends Pago {
    
    private String email;
    private double comision;

    public PagoPayPal(String idPago, String email, double comision) {
        super(idPago);
        this.email = email;
        this.comision = comision; 
    }

    @Override
    public boolean procesarPago(double importe) {
        double importeFinal = importe + comision;
        System.out.println("Cobrando " + importeFinal + "€ (incluye " + comision + "€ de comision) de: " + email);
        return true; 
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getComision() { return comision; }
    public void setComision(double comision) { this.comision = comision; }
}