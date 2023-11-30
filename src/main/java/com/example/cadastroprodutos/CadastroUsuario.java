package com.example.cadastroprodutos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroUsuario{

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField sobrenomeTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private Button cadastrarButton;
    @FXML
    private Button btnloginvolta;

        @FXML
        private void btnloginvolta(ActionEvent event) {
            //Scene scene = btnloginvolta.getScene();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();

                LoginController loginController = loader.getController();

                Scene cenaLogin = new Scene(root);

                Stage stage = (Stage) btnloginvolta.getScene().getWindow();

                stage.setScene(cenaLogin);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        private void cadastrarUsuario() {
            String nome = nomeTextField.getText();
            String sobrenome = sobrenomeTextField.getText();
            String email = emailTextField.getText();
            String senha = senhaPasswordField.getText();

            BancoDados connect = new BancoDados();
            Connection connectDB = connect.getConnection();

            if (connectDB != null) {
                try {
                    String query = "INSERT INTO login (nome, sobrenome, email, senha) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connectDB.prepareStatement(query);

                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, sobrenome);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, senha);

                    preparedStatement.executeUpdate();

                    // Usuário cadastrado com sucesso
                    System.out.println("Usuário cadastrado com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }

