package controller;

import dao.MovimientoDAO;
import view.MovimientoView;

public class MovimientoController {

    private MovimientoView view;

    private MovimientoDAO dao;

    public MovimientoController(
            MovimientoView view
    ) {

        this.view = view;

        this.dao = new MovimientoDAO();
    }
}