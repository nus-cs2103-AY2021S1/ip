package godfather;

import java.io.IOException;

import godfather.ui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final VitoCorleone vitoCorleone = new VitoCorleone();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); // AnchorPane is a node within the fxml file, so loads that node
            Scene scene = new Scene(ap); // place the nod onto the scene
            scene.getStylesheets().add("/view/mainStyle.css"); // one stylesheet to rule them all
            stage.getIcons().add(new Image("/images/marilyn3.png"));
            stage.setTitle("Godfather: keep to your word");
            stage.setResizable(false);
            stage.setScene(scene); // set the scene on the stage
            fxmlLoader.<MainWindow>getController().setDuke(this.vitoCorleone); // point the controller to Duke
            stage.show(); // show the stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
