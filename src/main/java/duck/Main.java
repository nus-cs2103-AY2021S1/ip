package duck;

import java.io.IOException;

import duck.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private Duck duck = new Duck();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Duck");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/duck.png"));
            stage.getIcons().add(icon);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();
            fxmlLoader.setController(mainWindow);
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            scene.getStylesheets().add("view/styles.css");
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuck(duck);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
