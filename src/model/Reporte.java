package model;

import java.time.LocalDateTime;

public class Reporte {

    private int idReporte;
    private LocalDateTime fechaGeneracion;
    private String tipoReporte;
    private Usuario usuario;

    public Reporte() {
    }

    public Reporte(
            int idReporte,
            LocalDateTime fechaGeneracion,
            String tipoReporte,
            Usuario usuario
    ) {

        this.idReporte = idReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.tipoReporte = tipoReporte;
        this.usuario = usuario;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(
            int idReporte
    ) {
        this.idReporte = idReporte;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(
            LocalDateTime fechaGeneracion
    ) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(
            String tipoReporte
    ) {
        this.tipoReporte = tipoReporte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(
            Usuario usuario
    ) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {

        return tipoReporte;
    }
}