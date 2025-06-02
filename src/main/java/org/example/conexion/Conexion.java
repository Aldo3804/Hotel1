package org.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "aldocarbajal38@";//

    public Conexion() throws SQLException {
    }


    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver no encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private Connection conexion =  getConexion();

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }

    public static void main(String[] args) throws SQLException {

        Conexion.getConexion();

    }



}
