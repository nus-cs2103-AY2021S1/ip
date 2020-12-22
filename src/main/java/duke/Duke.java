package duke;

import duke.exceptions.DukeException;
import duke.logic.commands.Command;
import duke.logic.commands.UpcomingTasksCommand;
import duke.logic.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a duke.Duke chatbot that can store, delete, mark tasks as done and display them.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a duke.Duke object that loads information from specified filePath.
     *
     * @param filePath The text file which duke.Duke loads information from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String showWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Shows reminder for events/deadline tasks that are a week away.
     *
     * @return The string message showing upcoming tasks if there are any, in a week.
     */
    public String showReminderMessage() {
        return (new UpcomingTasksCommand()).execute(tasks, ui, storage);
    }

    /**
     * Generates a response to user input from GUI.
     *
     * @param input The user input.
     * @return duke.Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, ui, storage);
            assert result != null : "No message is returned.";
            return result;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
