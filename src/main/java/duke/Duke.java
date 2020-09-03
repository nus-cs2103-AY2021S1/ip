package duke;

import duke.command.CommandResult;
import duke.exceptions.DukeException;
import duke.messages.Output;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the chat bot itself. Main class.
 */
public class Duke {

    private static final String DEFAULT_SAVE_PATH = "data/tasks.txt";

    private Output output;
    private Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     *
     * @param filePath A string representing the destination file path.
     */
    public Duke(String filePath) {
        output = new Output();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            output.printGeneralChatWindow(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Class constructor with no specified file path.
     */
    public Duke() {
        this(DEFAULT_SAVE_PATH);
    }

    /**
     * Executes a command based on the user input, and returns an appropriate response.
     *
     * @param input A string representing the user input.
     * @return A command result upon the execution of the command.
     * @throws DukeException If the input is invalid.
     */
    public CommandResult execute(String input) throws DukeException {
        return Parser.parse(input).execute(tasks, output, storage);
    }

}
