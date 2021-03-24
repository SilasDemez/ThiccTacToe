package tictactoe;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass()
                .getResource("tictactoe.fxml"));
        primaryStage.setTitle("ThiccTacToe");
        Scene scene = new Scene(root, 300, 325);
        scene.getStylesheets().add(getClass()
                .getResource("tictactoe.css").toExternalForm());
        primaryStage.getIcons().add(new Image("file:C:\\Users\\user\\Downloads\\gamification.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
