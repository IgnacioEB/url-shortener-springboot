package com.ignacio.urlshortener2.repository;

import com.ignacio.urlshortener2.database.ConexionBaseDeDatos;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UrlRepository {

    private final ConexionBaseDeDatos conexion;


    public UrlRepository(ConexionBaseDeDatos conexion) {
        this.conexion = conexion;
    }


    public void guardar(String codigo, String url) {

        String sql = "INSERT INTO urls (codigo, url) VALUES (?, ?)";

        try (Connection connection = conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigo);
            statement.setString(2, url);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo guardar la URL", e);
        }
    }

    public String buscar(String codigo) {

        String sql = "SELECT url,fecha_creacion FROM urls WHERE codigo = ?";

        try (Connection connection = conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigo);

            try (ResultSet resultado = statement.executeQuery()) {

                if (resultado.next()) {
                    return resultado.getString("url");
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo buscar la URL", e);
        }
    }

    public boolean existe(String codigo) {

        String sql = "SELECT 1 FROM urls WHERE codigo = ?";

        try (Connection connection = conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigo);

            try (ResultSet resultado = statement.executeQuery()) {
                return resultado.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo comprobar el código", e);
        }
    }



}