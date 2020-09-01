package blue;

import java.io.IOException;

import blue.command.Command;
import blue.command.CommandResponse;
import blue.exception.DukeException;
import blue.exception.ReadFailedException;
import blue.exception.UnknownInputException;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * The main class, Blue.
 */
public class Blue {
    /**
     * The Ui interacts with the user.
     */
    private final Ui ui;
    /**
     * The Storage deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;
    /**
     * The Tasks contains the task list.
     */
    private Tasks tasks;

    /**
     * Instantiates a new Blue.
     */
    public Blue() {
        this("data/tasks.txt");
    }

    /**
     * Instantiates a new Blue.
     *
     * @param filePath the file path of data to be read.
     */
    public Blue(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = initialiseStorage(filePath);
            this.tasks = storage.getTasks();
        } catch (ReadFailedException ex) {
            //            this.ui.printDukeException(ex);
            this.tasks = new Tasks();
        }
    }

    /**
     * Returns an Initialised storage.
     *
     * @param filePath the file path.
     * @return the initialised storage.
     * @throws ReadFailedException If reading the data failed.
     */
    private static Storage initialiseStorage(String filePath) throws ReadFailedException {
        try {
            return new Storage(filePath);
        } catch (IOException ex) {
            throw new ReadFailedException("data");
        }
    }

    /**
     * Create a command from the input, executes it and return the response.
     *
     * @param input the input.
     * @return the response.
     * @throws DukeException If the input is invalid.
     */
    public CommandResponse execute(String input) throws DukeException {
        Command command = Parser.parse(input);
        CommandResponse response = command.execute(tasks, ui, storage);
        if (response.isEmpty()) {
            throw new UnknownInputException();
        }
        return response;
    }
}
