import java.io.File;

import Command.Command;
import DukeComponent.Parser;
import TaskList.Storage;
import TaskList.TaskList;

/**
 * Duke asks user for their todos and makes a todo list.
 * Tasks can be viewed in a list, marked as done and deleted.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;

    /**
     * The default constructor for a Duke object.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage);
    }


    /**
     * This constructor takes in the file to save data.
     */
    public Duke(File file) {
        storage = new Storage(file);
        tasks = new TaskList(storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input, tasks.size());
        return command.act(tasks);
    }
}
