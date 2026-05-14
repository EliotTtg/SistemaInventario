package main;

import controller.LoginController;
import view.LoginView;

public class App {

    public static void main(String[] args) {

        LoginView view = new LoginView();

        new LoginController(view);
    }
}