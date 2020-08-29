package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import command.Command;
import command.ExitCommand;
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
     *
     * @param filePath Path and name of the file to be created and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath, DIR);
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
     * The main method for the chat bot application. Reads in a file path to create a Duke instance and run
     * the application.
     *
     * @param args 1 argument, filePath which is the path and name of the file to be created and loaded from.
     */
    public static void main(String[] args) {
        new Duke("data/serina.txt").run();
    }

    /**
     * The main chat bot application logic. Repeatedly reads in user commands and executes the commands until the
     * user exits the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c instanceof ExitCommand;
                }
            } catch (IllegalArgumentException ex) {
                ui.showError("I can't help you with that request, try something else.");
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }
}
