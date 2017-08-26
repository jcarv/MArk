package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("MArk - Music Ark");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/AppIcon32.png")));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.getScene().getStylesheets().add("/MArkTheme");
        //setUserAgentStylesheet("/MArkTheme");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
