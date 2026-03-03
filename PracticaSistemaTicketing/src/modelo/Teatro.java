package modelo;
public class Teatro extends Evento {

    private boolean diaEspectador;
    private boolean descuentoEstudiante;

    public Teatro(String id, String nombre, String lugar, Categoria categoria, boolean diaEspectador, boolean descuentoEstudiante) {
        super(id, nombre, lugar, categoria);
        
        this.diaEspectador = diaEspectador;
        this.descuentoEstudiante = descuentoEstudiante;
    }

    @Override
    public double getRecargoBase() {
        double multiplicador = 1.0;
        
        if (this.diaEspectador == true) { 
            multiplicador = multiplicador - 0.20; 
        }
        if (this.descuentoEstudiante == true) {
            multiplicador = multiplicador - 0.10;
        }
        
        return multiplicador;
    }

    @Override
    public double venderEntrada(int cantidad) {
        return getRecargoBase() * cantidad; 
    }

    
    public boolean isDiaEspectador() {
        return diaEspectador;
    }

    public void setDiaEspectador(boolean diaEspectador) {
        this.diaEspectador = diaEspectador;
    }

    public boolean isDescuentoEstudiante() {
        return descuentoEstudiante;
    }

    public void setDescuentoEstudiante(boolean descuentoEstudiante) {
        this.descuentoEstudiante = descuentoEstudiante;
    }
}