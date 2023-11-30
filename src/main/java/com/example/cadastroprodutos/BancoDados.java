package com.example.cadastroprodutos;

import java.sql.Connection;

public class BancoDados {
    public Connection link;

    public Connection getConnection() {
        String BancodadosNome = "cadastroprodutos";
        String BancodadosUsuario = "Gusttavo";
        String BancodadosSenha = "Gremio 1903";
        String url = "jdbc:mysql://localhost/" + BancodadosNome;
        try {
            if(link != null)
            Class.forName("com.mysql.jdbc.Driver");
            link = java.sql.DriverManager.getConnection(url, BancodadosUsuario, BancodadosSenha);

        } catch (Exception e) {
            System.out.println(e);
        }
        return link;
    }
}
