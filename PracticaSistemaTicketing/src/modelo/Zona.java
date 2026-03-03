package modelo;

public enum Zona {
    VIP("Es vip"),
    ECONOMICA("Es económica"),
    NORMAL("Es normal");

    private String descripcion;

    private Zona(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

}
