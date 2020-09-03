package duke.gui;

import duke.parser.Parser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Duke extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Label outputLabel = new Label("Hello World!"); // Creating a new Label control
        TextField inputField = new TextField();
        Button clearButton = new Button("Clear");
        Button submitButton = new Button("Submit");

        clearButton.setOnAction(e -> {
            inputField.clear();
        });
        submitButton.setOnAction(e -> {
            outputLabel.setText(inputField.getText());
        });

        GridPane gridPane = new GridPane();

        gridPane.add(outputLabel, 0, 0);
        gridPane.add(inputField, 0, 1);
        gridPane.add(clearButton, 1, 0);
        gridPane.add(submitButton, 1, 1);

        Scene scene = new Scene(gridPane); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
