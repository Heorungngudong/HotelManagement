package com.sgu.hotelmanagement.GUI;

import com.sgu.hotelmanagement.BUS.AccountBUS;
import com.sgu.hotelmanagement.BUS.IAuth_BUS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button loginButton;
    
    IAuth_BUS auth_bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        auth_bus = new AccountBUS();
        loginButton.setOnAction(event -> login());
    }

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (auth_bus.login(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login successfully");
            alert.showAndWait();

//            Scene scene = loginButton.getScene();
//            scene.setRoot(BootstrapFX.loadView(MainController.class));
        } else {
            System.out.println("Login failed");
        }
    }
}
