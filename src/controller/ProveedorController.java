package controller;

import dao.ProveedorDAO;
import view.ProveedorView;

public class ProveedorController {

    private ProveedorView view;

    private ProveedorDAO dao;

    public ProveedorController(
            ProveedorView view
    ) {

        this.view = view;

        this.dao = new ProveedorDAO();
    }
}