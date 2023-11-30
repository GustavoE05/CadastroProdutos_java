package com.example.cadastroprodutos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BancoDados {
    public Connection link;
    public boolean conectado = false;

    public String mensagem = "";

    public Connection getConnection() {
        String BancodadosNome = "cadastroprodutos";
        String BancodadosUsuario = "Gusttavo";
        String BancodadosSenha = "Gremio 1903";
        String url = "jdbc:mysql://localhost/" + BancodadosNome;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            link = java.sql.DriverManager.getConnection(url, BancodadosUsuario, BancodadosSenha);
            conectado = true;
            return link;
        }
        catch (ClassNotFoundException e){
            mensagem = e.toString();
        }
        catch (SQLException e){
            mensagem = e.toString();
        }
        return null;
    }
}
