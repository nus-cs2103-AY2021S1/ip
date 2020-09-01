package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTaskTypeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Entry point of Duke chatbot.
 * Initializes the chatbot and starts interaction with user.
 */
public class Duke {

    private static final String DATA_PATHNAME = "data/duke.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new instance of a Duke object.
     */
    public Duke() {
        storage = new Storage(DATA_PATHNAME);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printReply(e.getMessage());
        }
    }

    /**
     * Runs the Duke Chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
