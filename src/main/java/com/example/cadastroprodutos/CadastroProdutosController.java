package com.example.cadastroprodutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CadastroProdutosController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private TextField descricaoTextField;

    @FXML
    private Button btncadastraProdutos;

    private Connection connectDB;

    @FXML
    private void cadastrarProduto() {
            String nome = nomeTextField.getText();
            int quantidade = Integer.parseInt(quantidadeTextField.getText());
            double preco = Double.parseDouble(precoTextField.getText());
            String descricao = descricaoTextField.getText();

            try {
                BancoDados db = new BancoDados();
                connectDB = db.getConnection();

                if (db.conectado) {
                    String query = "INSERT INTO produto (nome, preco, quantidade, descricao) VALUES (?, ?, ?, ?)";
                    PreparedStatement requisicao = connectDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    requisicao.setString(1, nome);
                    requisicao.setDouble(2, preco);
                    requisicao.setInt(3, quantidade);
                    requisicao.setString(4, descricao);
                    requisicao.execute();

                    ResultSet resultado = requisicao.getGeneratedKeys();
                    if (resultado.next()) {
                        System.out.printf("Inserido " + resultado.getInt(1));
                    }

                    // Obtém a referência da janela atual
                    Stage stage = (Stage) nomeTextField.getScene().getWindow();

                    // Fecha a janela
                    stage.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Lidere com exceções SQL adequadamente
            }
       // if (connectDB != null) {
//        try{
//            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/CadastroProdutos" ,"Gusttavo", "Gremio 1903"))
//            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/CadastroProdutos" ,"Gusttavo", "Gremio 1903")){
//                // Passo 1: Obter o categoria_id associado à categoria selecionada
//
//
//                // Passo 2: Preparar a inserção no banco de dados
//                String query = "INSERT INTO produto (nome, preco, quantidade, descricao) VALUES (?, ?, ?, ?)";
//                try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
//                    preparedStatement.setString(1, nome);
//                    preparedStatement.setDouble(2, preco);
//                    preparedStatement.setInt(3, quantidade);
//                    preparedStatement.setString(4, descricao);
//                    preparedStatement.executeUpdate();
//                }
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Produtos.fxml"));
//                    Parent root = loader.load();
//
//                    // Obtenha o controlador da tela de cadastro (se necessário)
//                    ProdutosController produtosController = loader.getController();
//
//                    // Crie uma nova cena com a tela de cadastro
//                    Scene cenaProdutos = new Scene(root);
//
//                    // Obtenha a janela atual
//                    Stage stage = (Stage) btncadastraProdutos.getScene().getWindow();
//
//                    // Defina a nova cena na janela
//                    stage.setScene(cenaProdutos);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                // Produto adicionado com sucesso
//                System.out.println("Produto cadastrado com sucesso!");
//            } catch (SQLException e) {
//                System.out.println("Erro ao cadastrar produto: " + e.getMessage());
//            }
//        } else {
//            System.out.println("Falha ao conectar ao banco de dados." );
//        }
    }

}