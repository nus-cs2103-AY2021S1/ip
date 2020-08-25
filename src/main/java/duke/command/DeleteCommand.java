package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.StorageAccessException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

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
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize()) {
                throw new InvalidArgumentException("☹ OOPS!!! The task index you give is not found.");
            }
            String output = "Noted. I've removed this task:\n";
            output += ("\t " + tasks.getTask(index) + "\n");
            output += ("\t Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
            ui.displayMessage(output);
            tasks.deleteTask(index);
            storage.writeData(tasks.getTasks());
        } catch (InvalidArgumentException e) {
            ui.displayMessage(e.getMessage());
        } catch (IOException e) {
            throw new StorageAccessException("☹ OOPS!!! Something went wrong when removing the task from storage.");
        }
    }
}
