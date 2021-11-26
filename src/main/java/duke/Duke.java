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
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls the Duke UI to initialise and run.
     *
     * @throws IOException produced by failed or interrupted I/O operations
     * @throws DukeException thrown if the Duke program does not recognise user input
     */
    public void run() {
        Parser parser = new Parser(ui);
        Parser.setTasks(tasks);
        Parser.setFilePath(Storage.getFilePath());
    }

    public String getResponse(String input) throws IOException {
        Parser.setTasks(tasks);
        Parser.setFilePath(Storage.getFilePath());
        return Parser.parse(input);
    }

    /**
     * Creates a new Duke object with a specified filepath and runs it.
     *
     * @param args arguments for main function
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
