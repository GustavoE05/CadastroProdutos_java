package com.example.cadastroprodutos;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CategoriasContoller {

    @FXML
    private ListView<String> categoriasListView;

    @FXML
    private void initialize() {
        // Chame este método durante a inicialização do controlador para carregar as categorias
        carregarCategorias();
    }

    private void carregarCategorias() {
        // Carregue as categorias do banco de dados e adicione ao ListView
        BancoDados connect = new BancoDados();
        Connection connectDB = connect.getConnection();

        if (connectDB != null) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" ,"Gusttavo", "Gremio 1903")){
                String query = "SELECT nome FROM categoria";
                try (PreparedStatement preparedStatement = connectDB.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    ObservableList<String> categorias = FXCollections.observableArrayList();

                    while (resultSet.next()) {
                        String nomeCategoria = resultSet.getString("nome");
                        categorias.add(nomeCategoria);
                    }

                    categoriasListView.setItems(categorias);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao carregar categorias: " + e.getMessage());
            }
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
        }
    }

    }

