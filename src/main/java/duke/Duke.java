package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import undoStack.UndoStack;

/**
 * Returns typing box to allow user to type command.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Instantiates ui, read date from file.
     * Acts as default state if no data file is to be read.
     */
    public Duke() {
        try {
            UndoStack.initialize();
            storage = new Storage("./data/duke.txt");
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList(null);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
