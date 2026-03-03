package modelo;

public class Concierto extends Evento {

    private boolean artistaTop;
    private boolean zonaVip;


    public Concierto(String id, String nombre, String lugar, Categoria categoria, boolean artistaTop, boolean zonaVip) {
        super(id, nombre, lugar, categoria);
        this.artistaTop = artistaTop;
        this.zonaVip = zonaVip;
    }

    @Override
    public double getRecargoBase() {
        double multiplicador = 1.0; 
        
        if (this.artistaTop == true) { 
            multiplicador = multiplicador + 0.30; 
        }
        if (this.zonaVip == true) {
            multiplicador = multiplicador + 0.20;
        }
        
        return multiplicador;
    }

    @Override
    public double venderEntrada(int cantidad) {
        return getRecargoBase() * cantidad; 
    }

   
    public boolean isArtistaTop() {
        return artistaTop;
    }

    public void setArtistaTop(boolean artistaTop) {
        this.artistaTop = artistaTop;
    }

    public boolean isZonaVip() {
        return zonaVip;
    }

    public void setZonaVip(boolean zonaVip) {
        this.zonaVip = zonaVip;
    }
}
