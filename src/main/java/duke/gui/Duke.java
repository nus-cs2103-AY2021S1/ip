package duke.gui;

import duke.command.Command;
import duke.command.ReversibleCommand;
import duke.parser.DukeParserException;
import duke.parser.Parser;
import duke.task.Task;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * GUI application for Duke (Duke GUI)
 */
public class Duke extends Application {

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

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
        ListView<Task> taskView = new ListView<>(taskList);

        // Configure layout
        taskView.setFocusTraversable(false);
        clearButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        outputScrollPane.setContent(outputLabel);
        BorderPane leftPane = new BorderPane();
        leftPane.setPrefWidth(200d);
        leftPane.setTop(clearButton);
        leftPane.setCenter(outputScrollPane);
        leftPane.setBottom(inputField);
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(leftPane);
        mainPane.setCenter(taskView);

        // Display UI
        Scene scene = new Scene(mainPane); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.setTitle("Duke");
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
                System.out.println("> " + input);
                // 2. & 3. Parse input and run command
                try {
                    Command command = Parser.parse(taskList, input);
                    command.execute();
                    if (command instanceof ReversibleCommand) {
                        taskView.refresh(); // TODO: reduce performance hit
                    }
                } catch (DukeParserException e) {
                    System.out.println(e.getMessage());
                }
                // 4. Show output
                outputLabel.setText(outputStream.toString()); // Display output
                mainPane.layout(); // Refresh layout
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
