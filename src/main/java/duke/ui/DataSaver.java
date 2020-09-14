package duke.ui;

import duke.utils.Storage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Encapsulates a DataSaver class that saves data to a load file.
 */
public class DataSaver implements PopUpBox {
    private static boolean isQuitting = false;
    /**
     * Saves the data to a load file.
     *
     * @param storage the Storage object used to extract the data.
     */
    public static void save(Storage storage) {
        Stage window = new Stage();

        // disable actions in other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Are you sure?");
        window.setMinWidth(250);

        // create confirmation buttons
        Button saveAndQuit = new Button("Save and quit");
        Button notSaveAndQuit = new Button("Do not save and quit");
        Button cancel = new Button("Cancel");
        saveAndQuit.defaultButtonProperty().bind(saveAndQuit.focusedProperty());
        notSaveAndQuit.defaultButtonProperty().bind(notSaveAndQuit.focusedProperty());
        cancel.defaultButtonProperty().bind(cancel.focusedProperty());

        Label saveDataMessage = new Label();
        saveAndQuit.setOnAction(e -> {
            isQuitting = true;
            saveDataMessage.setText(storage.saveDataToFile());
            saveDataMessage.setText("Content has been Saved for you!");
            window.close();
        });

        notSaveAndQuit.setOnAction(e -> {
            isQuitting = true;
            window.close();
        });

        cancel.setOnAction(e -> {
            isQuitting = false;
            window.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(saveDataMessage, saveAndQuit, notSaveAndQuit, cancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Notifies if the program is going to quit.
     *
     * @return a boolean representing if the program is quitting.
     */
    public static boolean isQuitting() {
        return isQuitting;
    }
}
