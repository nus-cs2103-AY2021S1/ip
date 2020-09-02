package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.common.DukeException;
import duke.common.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the required objects and loads up the data from the storage file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/fileInfo.txt");
    }

    /**
     * Displays welcome message and accesses storage for saved list.
     */
    public void welcome() {
        Ui.displayWelcome();
        try {
            ArrayList<Task> currenttasks = Storage.readFile();
            tasks = new TaskList(currenttasks);
            if (!currenttasks.isEmpty()) {
                Ui.displayTasks(tasks);
            } else {
                Ui.display("No current list available. Start by adding a task!");
            }
        } catch (FileNotFoundException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                Ui.display(e.getMessage());
            }
        }
    }

    /**
     * Parses user input and executes command accordingly.
     */
    public void parseInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            Ui.display(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        parseInput(input);
        return "NIL";
    }
}
