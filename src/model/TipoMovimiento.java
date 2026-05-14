package model;

public class TipoMovimiento {

    private int idTipoMovimiento;
    private String nombre;
    private String descripcion;

    public TipoMovimiento() {
    }

    public TipoMovimiento(
            int idTipoMovimiento,
            String nombre,
            String descripcion
    ) {

        this.idTipoMovimiento =
                idTipoMovimiento;

        this.nombre = nombre;

        this.descripcion =
                descripcion;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(
            int idTipoMovimiento
    ) {
        this.idTipoMovimiento =
                idTipoMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(
            String nombre
    ) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(
            String descripcion
    ) {
        this.descripcion =
                descripcion;
    }

    @Override
    public String toString() {

        return nombre;
    }
}