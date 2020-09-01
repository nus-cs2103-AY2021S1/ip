package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for Project Duke.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Project Duke.
     * @param filePath The file path where the user's task list would be stored.
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui = new Ui();
        } catch (DukeException e) {
            assert false;
            ui.showLoadingError(e);
        }
    }

    public Duke() {}

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            if (tasks == null) {
                Storage storage = new Storage("data/tasks.txt");
                try {
                    tasks = storage.load();
                    ui = new Ui();
                } catch (DukeException e) {
                    assert false;
                    ui.showLoadingError(e);
                }
            }
            return Parser.parse(input, tasks, true);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initializes the Duke User Interface with the user's previously saved tasks (if any)
     */
    public void run() {
        ui.initializeDukeUI(tasks);
    }

    /**
     * The main method for Project Duke.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
