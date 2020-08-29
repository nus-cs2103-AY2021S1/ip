package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * TODO: add exit logic
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
