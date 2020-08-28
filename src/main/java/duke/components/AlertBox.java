package duke.components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Encapsulates an AlertBox class that deals with displaying an alert UI.
 */
public class AlertBox {
    /**
     * Displays an alert box with the given title and the alert message.
     *
     * @param title the title of the alert.
     * @param message the main message of the alert.
     */
    public static void display(String title, String message) {
        Stage window = new Stage();

        // disable actions in other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
