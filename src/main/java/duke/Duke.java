package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ReadFailedException;
import duke.task.Tasks;

/**
 * The main class, Duke.
 */
public class Duke {
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
     * Instantiates a new Duke.
     *
     * @param filePath the file path of data to be read.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = initialiseStorage(filePath);
            this.tasks = storage.getTasks();
        } catch (ReadFailedException ex) {
            this.ui.printDukeException(ex);
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
        Storage storage;
        try {
            storage = new Storage(filePath);
        } catch (IOException ex) {
            throw new ReadFailedException("data");
        }
        return storage;
    }

    /**
     * Instantiates Duke and runs it.
     * The main method.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs Duke by reading inputs using ui, generate commands using parser, executing commands and using
     * ui to print output.
     * Catch DukeExceptions and print it using ui.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                this.ui.printDukeException(ex);
            }
        }
    }
}
