package duke.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.core.DataStore;
import duke.core.Logic;
import duke.core.task.Task;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI application for Duke (Duke GUI)
 */
public class Duke extends Application {

    // Todo: Observable is deprecated.
    //  Replace observableList with java.beans.PropertyChangeListener
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    private final DataStore dataStore = new DataStore(taskList);

    @Override
    public void start(Stage stage) {

        // Redirect System.out to outputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Redirect System.err to errorStream
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        // UI Elements (button, textField, etc.)
        Button clearButton = new Button("Clear");
        VBox outputChatBox = new VBox();
        ScrollPane outputScrollPane = new ScrollPane();
        TextField inputField = new TextField();
        ListView<Task> taskView = new ListView<>(taskList);

        // Configure layout for UI Elements
        clearButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        outputChatBox.setSpacing(15);
        outputChatBox.setPrefSize(420, 400);
        outputScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        outputScrollPane.setContent(outputChatBox);
        taskView.setMinSize(300, 400);
        taskView.setFocusTraversable(false);
        taskView.setCellFactory(listView -> new IndexListCell<>());

        BorderPane leftPane = new BorderPane();
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

        Display display = new Display(outputChatBox);
        display.clear();

        // Clear the output screen
        clearButton.setOnAction(event -> display.clear());

        // Run main logic when user presses enter
        inputField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {

                // Print input
                String input = inputField.getText();
                display.in(input);
                inputField.setText("");

                // Execute logic
                Logic.execute(dataStore, input);

                // Print output
                display.out(outputStream.toString());
                outputStream.reset();

                // Print error
                display.err(errorStream.toString());
                errorStream.reset();

                // Autoscroll ScrollPane
                outputScrollPane.layout();
                outputScrollPane.setVvalue(1.0d);

                // Update list of task
                taskView.refresh();
            }
        });

    }

}
