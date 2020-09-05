package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * The main class for the project. Initialises the Duke class and runs it.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final String filePath = "data/data.txt";

    /**
     * Instantiates a Duke object.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.processStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String greetUser() {
        return "Hello! This is Duke\n" + "What can I do for you?";
    }
}
