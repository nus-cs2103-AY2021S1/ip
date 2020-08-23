package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.EventTask;
import task.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    String taskName;
    LocalDateTime timing;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task eventTask = new EventTask(this.taskName, this.timing);
        taskList.addTask(eventTask);
        ui.showAdd(eventTask, taskList.getSize());
    }

    public EventCommand(String taskName, LocalDateTime timing) {
        super(CommandType.Event);
        this.taskName = taskName;
        this.timing = timing;
    }

}
