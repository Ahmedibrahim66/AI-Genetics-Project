/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AyhamHashesh
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private Button logoutButton;

    @FXML
    void makeLogin(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        loginButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ReadData.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void makeLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
