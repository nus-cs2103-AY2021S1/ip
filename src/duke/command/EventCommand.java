package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.exception.DukeException;
import duke.task.EventTask;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * This class represents the event command.
 * When executed, the class will add the event task and save it
 * locally.
 */
public class EventCommand extends Command {
    private String taskName;
    private LocalDateTime date;

    /**
     * Constructs a Command for event with the specified task's name
     * and date.
     *
     * @param taskName the task's name
     * @param date the event's date
     */
    public EventCommand(String taskName, LocalDateTime date) {
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Executes the EventCommand. Executing this command will
     * create a new EventTask that will be added to the TaskList
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
        Task task = new EventTask(taskName, date);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}
