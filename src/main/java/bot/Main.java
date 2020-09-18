package bot;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The type Main.
 */
public class Main extends Application {

    /**
     * Initiates CLI of bot.
     *
     * @param args System args.
     */
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
            Label titleLabel = new Label("S\nT\nR\nA\nW\n \nB\nO\nT\n");
            titleLabel.setFont(new Font("Arial", 56));
            VBox rightControl = new VBox(titleLabel);

            SplitPane splitPane = new SplitPane();
            splitPane.getItems().addAll(scene.getRoot(), rightControl);
            Scene mainScene = new Scene(splitPane);

            stage.setScene(mainScene);
            stage.setTitle("STRAW BOT :)");
            fxmlLoader.<MainWindow>getController().setDuke("Straw Bot", "./assets/userData.txt");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
