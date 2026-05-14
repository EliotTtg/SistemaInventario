package controller;

import dao.CategoriaDAO;
import view.CategoriaView;

public class CategoriaController {

    private CategoriaView view;

    private CategoriaDAO dao;

    public CategoriaController(
            CategoriaView view
    ) {

        this.view = view;

        this.dao = new CategoriaDAO();
    }
}