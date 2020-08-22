package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

public class DoneOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    public DoneOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute() {
        Task completed = this.taskList.completeTask(this.index);
        return "You have completed this task:\n" + completed;
    }
}
