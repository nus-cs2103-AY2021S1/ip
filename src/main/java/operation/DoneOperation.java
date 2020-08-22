package operation;

import task.Task;
import task.TaskList;

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
    public void execute() {
        Task completed = this.taskList.completeTask(this.index);
        System.out.println("You have completed this task:\n" + completed);
    }
}
