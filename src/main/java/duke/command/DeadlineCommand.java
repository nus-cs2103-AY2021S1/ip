package duke.command;

import java.io.IOException;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private static String description;
    private static String by;

    /**
     * Constructor for a new DeadlineCommand object
     *
     * @param description details about the Deadline
     * @param by          date the deadline is due by in yyyy-mm-dd format
     */
    public DeadlineCommand(String description, String by) {
        DeadlineCommand.description = description;
        DeadlineCommand.by = by;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            Task temp = new Deadline(description, by);
            tasks.addTask(temp);
            Storage.appendToFile(temp);
            Ui.display("Yay! New task added:\n   "
                    + temp
                    + "\nNow you have "
                    + tasks.getSize()
                    + " tasks in your list.");
        } catch (Exception e) {
            throw new DukeException("Please input a suitable date of <yyyy-mm-dd> format!");
        }
    }
}
