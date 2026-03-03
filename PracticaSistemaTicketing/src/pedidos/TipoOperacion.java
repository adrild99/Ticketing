package pedidos;
public enum TipoOperacion {
    RESERVA("Está reservado"),
    COMPRA("Está comprado"),
    CANCELACION("Está cancelado");

    private String descripcion;

    private TipoOperacion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
