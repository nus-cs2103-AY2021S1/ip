package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke program implements an application that stores and keeps track of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to take in a specified filepath to create a Duke object.
     *
     * @param filePath the path location of the load or save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Calls the Duke UI to initialise and run.
     *
     * @throws IOException produced by failed or interrupted I/O operations
     * @throws DukeException thrown if the Duke program does not recognise user input
     */
    public void run() throws IOException, DukeException {
        Parser parser = new Parser(ui);
        parser.interact(tasks, Storage.getFilePath());
    }

    /**
     * Creates a new Duke object with a specified filepath and runs it.
     *
     * @param args arguments for main function
     * @throws IOException produced by failed or interrupted I/O operations
     * @throws DukeException thrown if the Duke program does not recognise user input
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
