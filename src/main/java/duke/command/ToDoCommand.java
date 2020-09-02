package duke.command;

import java.io.IOException;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Adds a ToDo task.
 */
public class ToDoCommand extends Command {
    private static String description;

    /**
     * Constructor for a new ToDoCommand object
     *
     * @param description details about the ToDo task
     */
    public ToDoCommand(String description) {
        ToDoCommand.description = description;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task temp = new ToDo(description);
        tasks.addTask(temp);
        Storage.appendToFile(temp);
        Ui.display("Yay! New task added:\n   "
                + temp
                + "\nNow you have " + tasks.getSize()
                + " tasks in your list.");
    }
}
