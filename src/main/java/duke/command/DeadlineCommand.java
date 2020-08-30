package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the deadline command.
 * When executed, the class will add the deadline task and save it
 * locally.
 */
public class DeadlineCommand extends Command {
    private String taskName;
    private LocalDateTime date;

    /**
     * Constructs a Command for deadline with the specified task's name
     * and date.
     *
     * @param taskName the task's name
     * @param date the deadline's date
     */
    public DeadlineCommand(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Executes the DeadlineCommand. Executing this command will
     * create a new DeadlineTask that will be added to the TaskList
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
        Task task = new DeadlineTask(taskName, date);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}
