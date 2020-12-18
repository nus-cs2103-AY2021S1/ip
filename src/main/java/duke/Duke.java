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
    public static final String FILE_PATH = "data/data.txt";
    public static final String GREETING = "Hello! This is Duke\nWhat can I do for you?";

    private final Storage storage;
    private TaskList tasks;

    /**
     * Instantiates a Duke object.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(FILE_PATH);
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
     * @return The {@link Response} given by Duke.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            return new Response(c.execute(tasks, storage), false);
        } catch (DukeException e) {
            return new Response(e.getMessage(), true);
        }
    }

    public static String greetUser() {
        return GREETING;
    }
}
