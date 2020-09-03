import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().update();
            stage.setTitle("Duke Bot");
            stage.show();

            //Add action when pressing on close button
            stage.setOnCloseRequest((event -> {
                fxmlLoader.<MainWindow>getController().handleCloseBtnClicked();
            }));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
