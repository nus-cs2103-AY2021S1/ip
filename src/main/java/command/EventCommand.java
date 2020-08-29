package command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.EventTask;
import task.Task;

public class EventCommand extends Command {
    private String taskName;
    private LocalDateTime timing;

    /**
     * Constructor for the event task
     *
     * @param taskName Name of task
     * @param timing   Time of task
     */
    public EventCommand(String taskName, LocalDateTime timing) {
        super(CommandType.Event);
        this.taskName = taskName;
        this.timing = timing;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task eventTask = new EventTask(this.taskName, this.timing);
        taskList.addTask(eventTask);
        ui.showAdd(eventTask, taskList.getSize());
    }
}
