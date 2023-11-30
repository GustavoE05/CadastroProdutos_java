package com.example.cadastroprodutos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button bntCadastrese;

    @FXML
    private TextField Usuariotext;

    @FXML
    private TextField Senhatext;

    @FXML
    private void btnCadastrarAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroUsuario.fxml"));
            Parent root = loader.load();

            // Obtenha o controlador da tela de cadastro (se necessário)
            CadastroUsuario cadastroController = loader.getController();

            // Crie uma nova cena com a tela de cadastro
            Scene cenaCadastro = new Scene(root);

            // Obtenha a janela atual
            Stage stage = (Stage) bntCadastrese.getScene().getWindow();

            // Defina a nova cena na janela
            stage.setScene(cenaCadastro);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void btnLoginAction(ActionEvent event) {
        // Código para tratar o botão "LOGIN"
        String usuario = Usuariotext.getText();
        String senha = Senhatext.getText();

        if (validarLogin(usuario, senha)) {
            System.out.println("Login bem-sucedido!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Produtos.fxml"));
                Parent root = loader.load();

                // Obtenha o controlador da tela de cadastro (se necessário)
                ProdutosController produtosController = loader.getController();

                // Crie uma nova cena com a tela de cadastro
                Scene cenaProdutos = new Scene(root);

                // Obtenha a janela atual
                Stage stage = (Stage) btnLogin.getScene().getWindow();

                // Defina a nova cena na janela
                stage.setScene(cenaProdutos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Senha ou usuário incorreto");
        }
    }

    private boolean validarLogin(String usuario, String senha) {
        BancoDados connect = new BancoDados();
        Connection connectDB = connect.getConnection();

        if (connectDB != null) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" ,"Gusttavo", "Gremio 1903")) {
                String query = "SELECT * FROM login WHERE nome = ? AND senha = ?";
                PreparedStatement preparedStatement = connectDB.prepareStatement(query);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, senha);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next(); // Retorna true se encontrar um usuário com a senha correspondente
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao validar login: " + e.getMessage());
            }
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
        }

        return false;
    }
}
