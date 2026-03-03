package modelo;

public enum ModoAforo {
    GENERAL("Es general"),
    NUMERADO("Es numerado");

    private String descripcion;

    private ModoAforo(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
