package pedidos;
import java.util.ArrayList;

public class Carrito {
    private ArrayList<Entrada> entradas = new ArrayList<>();
    private double total;

    public void addEntrada(Entrada e) { // añadir un elemento a la lista
        entradas.add(e);
    }

    public void removeEntrada(String id){ //retirar un elemento de la lista buscando el id
        for (int i = 0; i < this.entradas.size(); i++) {
            
            if (this.entradas.get(i).getIdEntrada().equals(id)) {
                this.entradas.remove(i); // La borramos de la lista
                break;
            }    
        }
    }

    public double calcularTotal() {
        this.total = 0.0; 
        
        for (Entrada e : this.entradas) {
            this.total = this.total + e.getPrecioFinal(); 
        }
        return this.total;
    }

    public void vaciar() { // vaciar la lista entera
        this.entradas.clear(); 
        this.total = 0.0;
    }
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
    public double getTotal() {
        return total;
    }
}
