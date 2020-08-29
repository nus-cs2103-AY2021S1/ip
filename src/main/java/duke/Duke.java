package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Platform;

public class Duke {
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private final Storage storage;
    private TaskList tasks;

    /**
     * Initialises a new instance of Duke
     */
    public Duke() {
        this.storage = new Storage(Duke.filePath);

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
     * Generates the Duke's response to the given input
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
