package clippy.command;

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
     * @param tasks Clippy.TaskList instance used by Clippy.Clippy in this session
     * @param ui Clippy.Ui instance used by Clippy.Clippy in this session
     * @param storage Clippy.Storage instance used by Clippy.Clippy in this session
     * @throws DateTimeParseException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws 
            DateTimeParseException {
        Task newTask = new Deadline(taskDescription, by);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());
        
        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
