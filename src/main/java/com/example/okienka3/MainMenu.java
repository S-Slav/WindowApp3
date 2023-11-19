package com.example.okienka3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainMenu {

    @FXML
    public Button employeeButton;
    @FXML
    public Button groupButton;
    @FXML
    public AnchorPane mainMenuPane;
    @FXML
    protected void onEmployeeButtonClick() throws Exception {
        EMMain m = new EMMain();
        m.changeScene("employee-menu.fxml");
    }
    @FXML
    protected void onGroupButtonClick()throws IOException {
        EMMain m = new EMMain();
        m.changeScene("group-menu.fxml");
    }
}