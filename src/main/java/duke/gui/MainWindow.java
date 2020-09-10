package duke.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.core.DukeData;
import duke.core.DukeLogic;
import duke.core.task.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * GUI application for Duke (Duke GUI)
 */
public class MainWindow extends BorderPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField inputField;
    @FXML
    private ListView<Task> taskView;

    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

    private DukeData dukeData;
    private Display display;

    @FXML
    public void initialize() {
        // Redirect System.out to outputStream
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Redirect System.err to errorStream
        errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        // Duke variables
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        dukeData = new DukeData(taskList);
        display = new Display(dialogContainer);

        // fxml variables
        taskView.setItems(taskList);
        taskView.setCellFactory(listView -> new IndexListCell<>());

        // Autoscroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Print greeting
        display.clear();
    }

    /**
     * Run main logic when user presses enter
     */
    @FXML
    private void handleUserInput() {
        // Main program logic
        display.showInput(inputField.getText());
        DukeLogic.execute(dukeData, inputField.getText());
        display.showOutput(outputStream.toString());
        display.showError(errorStream.toString());

        // Force display to refresh taskList
        taskView.refresh();

        // Clear all Input/Output
        inputField.clear();
        outputStream.reset();
        errorStream.reset();
    }

    /**
     * Clear the output screen
     */
    @FXML
    private void clearDisplay() {
        display.clear();
    }

}
