package duke;

import java.io.IOException;
import java.util.List;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke {

    public static final String FILE_DIRECTORY = "data/";
    public static final String FILE_NAME = "duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollpane;
    private VBox dialogContainer;
    private Button sendButton;
    private TextField userInput;

    /**
     * Creates a new Duke object that loads input from the database file.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage(FILE_DIRECTORY, FILE_NAME);
            List<Task> taskList = storage.generateTaskList();
            tasks = new TaskList(storage.generateTaskList());
        } catch (IOException | DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program on the Command Line Interface until
     * a valid exit input is typed in by the user.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (!fullCommand.isEmpty()) {
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException | IOException e) {
                ui.showError(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public String getResponse(String userText) throws DukeException, IOException {
        String fullCommand = userText.trim();
        Command c = Parser.parse(fullCommand);
        return c.execute(tasks, ui, storage);
    }
}
