package modelo;

public class Asiento {
    private String idAsiento;
    private boolean reservado;
    private Zona zona;

    public Asiento(String idAsiento, boolean reservado, Zona zona){
        this.idAsiento = idAsiento;
        this. reservado = reservado;
        this.zona = zona;
    }

    public double getMultiplicadorZona(){
        if (this.zona == Zona.VIP) {
            return 1.5;
        } else if (this.zona == Zona.ECONOMICA) {
            return 0.8;
        }
        else
            return 1.0;
    }

    public String getIdAsiento (){
        return idAsiento;
    }
    
    public boolean getReservado (){
        return reservado;
    }
    public Zona getZona(){
        return zona;
    }

    public void setIdAsiento(String idAsiento){
        this.idAsiento = idAsiento;
    }
    public void setReservado (boolean reservado){
        this.reservado = reservado;
    }


}  
