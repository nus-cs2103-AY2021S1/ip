package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Represents the Duke chat bot.
 */
public class Duke {
    /**
     * The file path where tasks will be stored.
     */
    private static final Path FILE_PATH = Paths.get(".", "data", "duke.txt");

    /**
     * The storage associated with the chat bot.
     */
    private final Storage storage;

    /**
     * The list of tasks associated with the chat bot.
     */
    private TaskList tasks;

    /**
     * Initialises a new instance of Duke.
     */
    public Duke() {
        this.storage = new Storage(Duke.FILE_PATH);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the message that Duke will use to greet the user when first launching the
     * application.
     *
     * @return The greeting message.
     */
    public String greetUser() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }


    /**
     * Generates the Duke's response to the given input.
     *
     * @param input The input by the user.
     * @return The response given by Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
