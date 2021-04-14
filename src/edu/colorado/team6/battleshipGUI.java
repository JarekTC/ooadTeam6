package edu.colorado.team6;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;

public class battleshipGUI extends Application {

    private Parent root1;
    private Parent root2;
    int whichScene = 0;

    // Player one scene
    private Scene scene1;

    // Player two scene
    private Scene scene2;

    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        root1 = FXMLLoader.load(getClass().getResource("battleshipGUI.fxml"));
        root2 = FXMLLoader.load(getClass().getResource("battleshipGUI.fxml"));
        root1.getStylesheets().add(getClass().getResource("./UI.css").toExternalForm()); // for when we CSS
        root2.getStylesheets().add(getClass().getResource("./UI.css").toExternalForm()); // for when we CSS

        // Player one scene
        scene1 = new Scene(root1, 658, 597);

        // Player two scene
        scene2 = new Scene(root2, 658, 597);

        window.setScene(scene1);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);   //launches the GUI
    }

    @FXML
    public void setScene(MouseEvent mouseEvent) {
        if (whichScene == 0) {
            window.setScene(scene2);
            whichScene = 1;
        }
        if (whichScene == 1){
            window.setScene(scene1);
            whichScene = 0;
        }
    }

}

