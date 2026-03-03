package modelo;

public class Cine extends Evento {

    private boolean es3D;
    private boolean esEstreno;

    public Cine(String id, String nombre, String lugar, Categoria categoria, boolean es3D, boolean esEstreno) {
        super(id, nombre, lugar, categoria);         
        this.es3D = es3D;
        this.esEstreno = esEstreno;
    }

    @Override
    public double getRecargoBase() {
        double multiplicador = 1.0; 
        
        if (this.es3D == true) { 
            multiplicador = multiplicador + 0.20; 
        }
        if (this.esEstreno == true) {
            multiplicador = multiplicador + 0.10;
        }
        
        return multiplicador;
    }

    @Override
    public double venderEntrada(int cantidad) {
        return getRecargoBase() * cantidad; 
    }
}
