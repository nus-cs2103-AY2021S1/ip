package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import command.Command;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents the Duke class. The Duke class serves as the main entry point into the chatbot application.
 */
public class Duke {
    /**
     * File directory where the text file is stored
     */
    private static final String DIR = "data";
    /**
     * Path and name of the file to be created and loaded from.
     */
    private static final String FILE_PATH = "data/serina.txt";
    /**
     * Ui to print responses to user
     */
    private final Ui ui;
    /**
     * Storage where writing and reading of files is handled
     */
    private Storage storage;
    /**
     * List of tasks
     */
    private TaskList tasks;
    /**
     * Parser to parse and verify user input
     */
    private Parser parser;

    /**
     * Creates a Duke instance and loads user tasks. Creates new file if there is no existing file.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(FILE_PATH, DIR);
            tasks = new TaskList(storage.loadData());
            parser = new Parser(tasks);
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            parser = new Parser(tasks);
            System.out.println("----- You have no tasks saved as of yet. Feel free to add tasks and I will track"
                    + " them for you");
        } catch (IOException e) {
            System.out.println("----- Something went wrong, please try again later");
        }
    }

    /**
     * The main chat bot application logic. Repeatedly reads in user commands and executes the commands until the
     * user exits the application.
     *
     * @param input User input command.
     * @return returns appropriate response after command execution.
     */
    public String getResponse(String input) {
        String result;
        try {
            Command c = parser.parse(input);
            result = c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException ex) {
            result = ui.showError("I can't help you with that request, try something else.");
        } catch (DukeException ex) {
            result = ui.showError(ex.getMessage());
        }
        System.out.println(result);
        assert !result.isEmpty() : "Response should not be empty";
        return result;
    }

    /**
     * Getter method to get the initialized Ui.
     *
     * @return Ui
     */
    public Ui getUi() {
        return ui;
    }
}
