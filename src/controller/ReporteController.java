package controller;

import dao.ReporteDAO;
import view.ReporteView;

public class ReporteController {

    private ReporteView view;

    private ReporteDAO dao;

    public ReporteController(
            ReporteView view
    ) {

        this.view = view;

        this.dao = new ReporteDAO();
    }
}