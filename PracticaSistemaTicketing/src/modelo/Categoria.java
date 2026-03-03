package modelo;

public enum Categoria {
    CONCIERTO("Es concierto"),
    TEATRO("Es teatro"),
    CINE("Es cine");

    private String descripcion;

    private Categoria(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}