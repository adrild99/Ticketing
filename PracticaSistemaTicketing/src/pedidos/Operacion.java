package pedidos;
import java.util.ArrayList;

public class Operacion {
    private TipoOperacion tipo;
    private String detalle;
    private ArrayList <Entrada> entradasAfectadas = new ArrayList<>();

    public Operacion(TipoOperacion tipo, String detalle, ArrayList<Entrada> entradasAfectadas){
        this.tipo = tipo;
        this.detalle = detalle;
        
        for (Entrada e : entradasAfectadas) {
            this.entradasAfectadas.add(e);
        }
    }

    @Override
    public String toString() {
        return "Operación [" + tipo + "] - " + detalle + " (" + entradasAfectadas.size() + " entradas)";
    }
    
    public TipoOperacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperacion tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public ArrayList<Entrada> getEntradasAfectadas() {
        return entradasAfectadas;
    }

    public void setEntradasAfectadas(ArrayList<Entrada> entradasAfectadas) {
        this.entradasAfectadas = entradasAfectadas;
    }
}
