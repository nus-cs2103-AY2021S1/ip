package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.StorageAccessException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    /**
     * Index of {@link Task} to be deleted.
     */
    private final int index;

    /**
     * Instantiates a new DeleteCommand object.
     * @param index The index of the {@link Task} to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to delete the {@link Task} and save the changes to storage.
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize()) {
                throw new InvalidArgumentException("☹ OOPS!!! The task index you give is not found.");
            }
            String output = "Noted. I've removed this task:\n";
            output += ("" + tasks.getTask(index) + "\n");
            output += ("Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
            tasks.deleteTask(index);
            storage.writeData(tasks.getTasks());
            return output;
        } catch (InvalidArgumentException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new StorageAccessException("☹ OOPS!!! Something went wrong when removing the task from storage.");
        }
    }
}
