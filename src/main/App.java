package main;

import config.ConexionDB;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

        try (
                Connection con = ConexionDB.conectar()
        ) {

            System.out.println("Conexion exitosa a MySQL");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}