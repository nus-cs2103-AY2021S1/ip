package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Personal Assistant that keeps track of a user's list of tasks.
 */
public class Duke {
    private static final Path storageFilePath = Paths.get(".", "data", "test.txt");
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor to create a Duke object.
     * <p>
     * Duke consists of a <code>TaskList</code>, and <code>Storage</code> which
     * is responsible for user interactions, keeping track of user's tasks, and writing tasks
     * to the user's local storage respectively.
     */
    public Duke() {
        this.storage = new Storage(Duke.storageFilePath);
        try {
            this.taskList = new TaskList(this.storage.getAllTasks());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Gets Duke's response to the user input.
     *
     * @param input the input by the user via the GUI.
     * @return the response given by Duke to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }

            return c.execute(this.taskList, this.storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public String greet() {
        return "Hello! I am Duke, your personal assistant, how can I help you today?";
    }
}
