package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.commands.ExceptionCommand;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the main class. */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /** Constructs a new Duke object. */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        ArrayList<String> lines = storage.readFile();
        parser = new Parser();
        ArrayList<Task> tasks = parser.parseSavedTaskList(lines);
        taskList = new TaskList(tasks);
    }

    /** Runs the Duke program. */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            try {
                Command c = getResponse(userInput);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    /** Gets the response in Command form from the user input.
     *
     * @param userInput The user input.
     * @return The response in Command form.
     */
    public Command getResponse(String userInput) {
        try {
            Command c = parser.parse(userInput);
            c.execute(taskList, ui, storage);
            return c;
        } catch (Exception e) {
            Command c = new ExceptionCommand(e);
            c.execute(taskList, ui, storage);
            return c;
        }
    }

    /** The main method which runs the Duke program.
     *
     * @param args The array of command-line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
