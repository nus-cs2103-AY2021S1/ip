package duke.components;

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
public class DataSaver {
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

        Label label = new Label("You are going to exit Duke!");

        // create confirmation buttons
        Button saveAndQuit = new Button("Save and quit");
        Button notSaveAndQuit = new Button("Do not save and quit");
        Button cancel = new Button("Cancel");

        saveAndQuit.setOnAction(e -> {
            isQuitting = true;
            storage.saveDataToFile(label);
            label.setText("Content has been Saved for you!");
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
        layout.getChildren().addAll(label, saveAndQuit, notSaveAndQuit, cancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    public static boolean isQuitting() {
        return isQuitting;
    }
}
