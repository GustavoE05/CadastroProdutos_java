package com.example.cadastroprodutos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutosController {

    @FXML
    private Button Categorias;

    @FXML
    private Label listaProdutos;

    @FXML
    private ListView<String> productListView; // Ajuste o tipo de dado conforme a sua implementação

    @FXML
    private void initialize() {
        // Chame este método durante a inicialização da tela para carregar a lista de produtos
        carregarListaProdutos();


    }

    public void atualizarListaProdutos() {
        carregarListaProdutos(); // Atualiza a lista de produtos
    }

    private void carregarListaProdutos() {
        BancoDados connect = new BancoDados();
        Connection connectDB = connect.getConnection();

        if (connectDB != null) {
            try {
                String query = "SELECT * FROM produto";
                PreparedStatement preparedStatement = connectDB.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Limpa o ListView antes de adicionar novos itens
                productListView.getItems().clear();

                // Adiciona os nomes dos produtos ao ListView
                while (resultSet.next()) {
                    String nomeProduto = resultSet.getString("nome");

                    double precoProduto = resultSet.getDouble("preco");

                    int quantidadeProduto = resultSet.getInt("quantidade");

                    String descricaoProduto = resultSet.getString("descricao");

                    //String categoriaProduto = resultSet.getString("categoria");

                    String infoProduto = String.format("Nome: %s%nPreço: %.2f%nQuantidade: %d%nDescrição: %s", nomeProduto, precoProduto, quantidadeProduto, descricaoProduto);

                    productListView.getItems().add(infoProduto);
                }
                atualizarListaProdutos();
            } catch (SQLException e) {
                System.out.println("Erro ao carregar lista de produtos: " + e.getMessage());
            }
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
        }
    }

    @FXML
    private void mostrarTelaCadastroProdutos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroProdutos.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Cadastro de Produtos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarTelaCategorias(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorias.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Categorias");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

