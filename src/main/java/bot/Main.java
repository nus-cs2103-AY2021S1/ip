package bot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Main extends Application {
    private Bot duke = new Bot("Straw Bot", "./assets/userData.txt");

    public static void main(String[] args) {
        Bot bot = new Bot("Straw Bot", "./assets/userData.txt");
        bot.init(new Scanner(System.in));
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("STRAW BOT :)");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}