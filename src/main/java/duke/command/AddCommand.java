package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.StorageAccessException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a {@link Command} that will add a {@link Task} to the {@link TaskList}.
 */
public class AddCommand extends Command {
    /**
     * The Task to be added.
     */
    private final Task task;

    /**
     * Instantiates a new AddCommand object.
     * @param task The {@link Task} to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to add the {@link Task} and save it to storage.
     * @param tasks The list of {@link Task}s.
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     * @throws DukeException Exception when writing data to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        String output = "Got it. I've added this task:\n";
        output += ("\t " + task.toString() + "\n");
        output += ("\t Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task") + " in the list.");
        ui.displayMessage(output);
        try {
            storage.writeData(tasks.getTasks());
        } catch (IOException e) {
            throw new StorageAccessException("☹ OOPS!!! Something went wrong when saving the new task.");
        }
    }
}
