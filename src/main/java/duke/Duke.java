package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the greenfield project Duke that manages user tasks.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private TaskManager taskManager;

    /**
     * Constructs a duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.taskManager = new TaskManager(taskList, ui, storage, parser);
        try {
            // Try to load data from text file
            this.storage.loadFromTextFile(this.taskList);
        } catch (FileNotFoundException e) {
            // If text file does not exist yet, create the text file
            this.storage.createFile();
        }
    }

    /**
     * Initialises the program by using ui object to display the welcome message,
     * and calls on taskManager object to begin requesting and processing user input.
     */
    public void run() {
        this.ui.printWelcomeMessage();
        this.taskManager.manage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            try {
                Command c = Parser.parse(input);
                CommandResult commandResult = c.execute(taskList, ui, storage);
                return commandResult.getMessageAfterExecution();
            } catch (NumberFormatException error) {
                // When "done" is not followed by a valid number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (ArrayIndexOutOfBoundsException error) {
                // When "done" is not followed by any number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (IndexOutOfBoundsException error) {
                // When "done is followed by a number that is out of range
                throw new DukeException("OOPS!!! That index is out of range!");
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! Please enter the date in yyyy-mm-dd format!");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
        // Required because the catch block for DukeException does not throw/return
        return "Invalid input";
    }

    /**
     * Drives program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
