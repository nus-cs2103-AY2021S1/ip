package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.DeadlineTask;
import task.Task;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeadlineCommand that = (DeadlineCommand) o;
        return Objects.equals(taskName, that.taskName) &&
                Objects.equals(deadline, that.deadline);
    }

}
