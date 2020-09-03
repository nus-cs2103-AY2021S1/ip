package duke.gui;

import duke.parser.DukeParserException;
import duke.parser.Parser;
import duke.task.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI application for Duke (Duke GUI)
 */
public class Duke extends Application {

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private final List<Task> taskList = new ArrayList<>(100);

    @Override
    public void start(Stage stage) throws Exception {

        // Redirect System.out to outputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // UI Elements (button, textField, etc.)
        ScrollPane outputScrollPane = new ScrollPane();
        Label outputLabel = new Label(WELCOME_MESSAGE);
        TextField inputField = new TextField();
        Button clearButton = new Button("Clear");

        // UI Elements config
        outputScrollPane.setPrefSize(400, 300);
        outputScrollPane.setContent(outputLabel);

        // Configure layout
        GridPane gridPane = new GridPane();
        gridPane.add(outputScrollPane, 0, 0);
        gridPane.add(inputField, 0, 1);
        gridPane.add(clearButton, 0, 2);

        // Display UI
        Scene scene = new Scene(gridPane); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // Add Action Listeners

        // When user press enter
        // 1. Read input from inputField
        // 2. Parse input into command
        // 3. Run command
        // 4. Show output
        inputField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                // 1. Read input
                String input = inputField.getText();
                inputField.setText("");
                // 2. & 3. Parse input and run command
                try {
                    Parser.parse(taskList, input).execute();
                } catch (DukeParserException e) {
                    System.out.println(e.getMessage());
                }
                // 4. Show output
                outputLabel.setText(outputStream.toString()); // Display output
                gridPane.layout(); // Refresh layout
                outputScrollPane.setVvalue(1.0d); // Automatically scroll down
            }
        });

        // Clear the output screen
        clearButton.setOnAction(event -> {
            outputStream.reset();
            outputLabel.setText(WELCOME_MESSAGE);
        });
    }
}
