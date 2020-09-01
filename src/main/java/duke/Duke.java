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
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/data/duke.txt");
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

    public String getResponse(String input) throws IOException {
        return Parser.interact(input, tasks, Storage.getFilePath());
    }

    /**
     * Creates a new Duke object with a specified filepath and runs it.
     *
     * @param args arguments for main function
     * @throws IOException produced by failed or interrupted I/O operations
     * @throws DukeException thrown if the Duke program does not recognise user input
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }
}
