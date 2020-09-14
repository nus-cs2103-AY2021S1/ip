package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deletes the specified task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a Command object for task deletion.
     *
     * @param command is the input given from the user.
     * @param index is the index of the task to be deleted.
     */
    public DeleteCommand(String command, String index) {
        super(command);
        this.index = Integer.parseInt(index);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task selectedTask = null;
        try {
            selectedTask = tasks.deleteTask(index);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "Noted. I've removed this task:\n " + selectedTask
                + "\nYou now have " + tasks.size() + " task(s) in the list.";
    }

}
