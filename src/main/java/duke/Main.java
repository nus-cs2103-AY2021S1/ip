package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }

    /**
     * Method to start running GUI for Duke.
     *
     * @param stage staging area.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            ToolBar toolBar = new ToolBar();
            stage.setResizable(false);
            int height = 25;
            toolBar.setPrefHeight(height);
            toolBar.setMinHeight(height);
            toolBar.setMaxHeight(height);
            toolBar.getItems().add(new WindowButtons());
            stage.setTitle("PikaDuke To-Do List");
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/sendbutton.png"));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To end duke.
     */
    public static void endDuke() {
        Platform.exit();
    }
}
