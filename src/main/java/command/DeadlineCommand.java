package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.DeadlineTask;
import task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    String taskName;
    LocalDateTime deadline;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deadlineTask = new DeadlineTask(this.taskName, this.deadline);
        taskList.addTask(deadlineTask);
        ui.showAdd(deadlineTask, taskList.getSize());
    }

    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        super(CommandType.Deadline);
        this.taskName = taskName;
        this.deadline = deadline;
    }

}
