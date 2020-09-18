package clippy.command;

import clippy.exception.InvalidDateFormatException;
import clippy.storage.Storage;
import clippy.task.Deadline;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command that adds a deadline task when executed.
 */
public class AddDeadlineCommand extends AddCommand {
    private String by;

    /**
     * Constructs a command object that adds a deadline task when executed.
     * 
     * @param taskDescription Literal description of the deadline task.
     * @param by Date in YYYY-MM-DD format as a String.
     */
    public AddDeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Returns resulting message to be displayed by GUI after adding a deadline task.
     * Executes the command.
     * Saves changes onto save file.
     * 
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after adding a deadline task.
     * @throws InvalidDateFormatException If command has date in a format that is not YYYY-MM-DD.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws 
            InvalidDateFormatException {
        Task newTask = new Deadline(taskDescription, by);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);

        return output;
    }
}
