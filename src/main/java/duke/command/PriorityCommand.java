package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Priority;
import duke.task.Task;

public class PriorityCommand implements Command {

    private final int taskNumber;
    private final Priority priority;

    public PriorityCommand(int taskNumber, Priority priority) {
        this.taskNumber = taskNumber;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        task.addPriority(priority);
        return ui.displayTaskAddPriority(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
