package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the todo command.
 * When executed, the class will add the todo task and save it
 * locally.
 */
public class ToDoCommand extends Command {
    private String taskName;

    /**
     * Constructs a Command for event with the specified task's name.
     *
     * @param taskName the task's name
     */
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the ToDoCommand. Executing this command will
     * create a new ToDoTask that will be added to the TaskList
     * and will be saved to the hard disk by Storage. The Ui will shown
     * the corresponding message depending on whether an exception is thrown
     * or not.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException If the storage fails to save to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDoTask(taskName);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}
