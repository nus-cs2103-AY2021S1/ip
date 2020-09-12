package clippy.command;

import clippy.exception.InvalidDateFormatException;
import clippy.storage.Storage;
import clippy.task.Deadline;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents command that adds deadline-typed tasks.
 */
public class AddDeadlineCommand extends AddCommand {
    protected String by;

    /**
     * Constructs command object that adds deadline-typed tasks
     * @param taskDescription literal description of the task
     * @param by date in YYYY-MM-DD format as a String
     */

    public AddDeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Executes the command.
     * @param tasks TaskList instance used by Clippy in this session
     * @param ui Ui instance used by Clippy in this session
     * @param storage Storage instance used by Clippy in this session
     * @throws DateTimeParseException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws 
            InvalidDateFormatException {
        try {
            Task newTask = new Deadline(taskDescription, by);
            tasks.add(newTask);
            String output = ui.showAdded(newTask, tasks.size());

            tasks.updateAllTaskIndices();
            storage.save(tasks);

            return output;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
