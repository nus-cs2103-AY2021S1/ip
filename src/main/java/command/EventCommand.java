package command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.EventTask;
import task.Task;

/**
 * Class to initiate the Event Command
 */
public class EventCommand extends Command {
    private String taskName;
    private LocalDateTime timing;

    /**
     * Constructor for the event task
     *
     * @param taskName Name of task.
     * @param timing   Time of task.
     */
    public EventCommand(String taskName, LocalDateTime timing) {
        super(CommandType.Event);
        this.taskName = taskName;
        this.timing = timing;
    }

    /**
     * Runs the command to add a new Event Task into the TaskList
     *
     * @param taskList ArrayList of Tasks Objects.
     * @param ui       Object of the Ui class.
     * @param storage  Object of the Storage class.
     * @throws DukeException Exception that occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task eventTask = new EventTask(this.taskName, this.timing);
        taskList.addTask(eventTask);
        return ui.showAdd(eventTask, taskList.getSize());
    }

}
