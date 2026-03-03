package pedidos;
import java.util.ArrayList;

import pagos.Pago;

public class Pedido {

    private static int contadorPedidos = 1;

    private String idPedido;
    private EstadoPedido estado;
    private ArrayList <Entrada> entradas = new ArrayList<>();

    private Pago pago;
    private double total;

    public Pedido(Carrito carrito, Pago pago){
        this.idPedido = String.format("PED-%03d", contadorPedidos);
        contadorPedidos++;
        
        for (Entrada e : carrito.getEntradas()) {
            this.entradas.add(e); 
        }        
        this.pago = pago;
        this.estado = EstadoPedido.PENDIENTE;
        this.total = calcularTotal();
    }

    public double calcularTotal(){
        double suma = 0.0;
        
        for (Entrada e : this.entradas) {
            suma = suma + e.getPrecioFinal();
        }
        return suma;    
    }

    @Override
    public String toString() {
        return "PEDIDO: " + idPedido + ",  Estado: " + estado + 
               ", Total: " + total + "euros, Nº Entradas: " + entradas.size();
    }

    public String getIdPedido() { 
        return idPedido; 
    }

    public EstadoPedido getEstado() { 
        return estado; 
    }

    public ArrayList<Entrada> getEntradas() { 
        return entradas; 
    }

    public Pago getPago() { 
        return pago; 
    }

    public double getTotal() { 
        return total; 
    }

    public void setEstado(EstadoPedido estado) { 
        this.estado = estado; 
    }
}
