package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private static String description;
    private static String by;

    public DeadlineCommand(String description, String by) {
        DeadlineCommand.description = description;
        DeadlineCommand.by = by;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
        try {
            Task temp = new Deadline(description, by);
            tasks.addTask(temp);
            Storage.appendToFile(temp);
            Ui.display("Yay! New task added:\n   " + temp +
                    "\nNow you have " + tasks.getSize() + " tasks in your list.");
        } catch (Exception e) {
            throw new CustomException("Please input a suitable date of <yyyy-mm-dd> format!");
        }
    }
}
