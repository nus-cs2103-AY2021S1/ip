package duke;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Pop up box class for use for Duke GUI
 *
 * @author YanCheng
 */
public class PopUpBox {

    /**
     * Displays a pop up box with the specified message
     * @param title Title of the window
     * @param message Message to be displayed in the window
     */
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.setTitle(title);

        Label label = new Label();
        label.setText(message);

        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 200);
        window.setScene(scene);
        window.show();
    }
}
