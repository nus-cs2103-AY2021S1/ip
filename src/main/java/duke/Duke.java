package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements the chatbot application
 *
 * @author Audrey Felicio Anwar
 */
public class Duke {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a Duke object.
     */
    public Duke() {
        this("./tasks.txt");
    }

    /**
     * Initializes a Duke object
     *
     * @param filePath The location of saved data.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException error) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a response to be shown to the user.
     *
     * @param input Input from the user.
     * @return Response to the user.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            response.append(ui.showLine() + SEPARATOR);
            Command command = Parser.parse(input);
            response.append(command.executeCommand(tasks, ui, storage) + SEPARATOR);
        } catch (DukeException error) {
            response.append(error.getMessage() + SEPARATOR);
        } finally {
            response.append(ui.showLine() + SEPARATOR);
        }
        return response.toString();
    }

    /**
     * Shows welcome message to user.
     *
     * @return Welcome message to user.
     */
    public String getWelcomeMessage() {
        return ui.getGreetings();
    }
}
