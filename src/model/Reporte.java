package model;

import java.time.LocalDateTime;

public class Reporte {

    private int idReporte;
    private TipoReporte tipoReporte;
    private LocalDateTime fechaGeneracion;
    private int idUsuario;

    public Reporte() {
    }

    public Reporte(int idReporte,
                   TipoReporte tipoReporte,
                   LocalDateTime fechaGeneracion,
                   int idUsuario) {

        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.idUsuario = idUsuario;
    }

    public void generarReporteStock() {

        System.out.println("Reporte stock generado");
    }

    public void generarReporteMovimientos() {

        System.out.println("Reporte movimientos generado");
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}