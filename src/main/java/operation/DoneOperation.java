package operation;

import task.Task;
import task.TaskStorage;

public class DoneOperation extends Operation {
    private final TaskStorage taskStorage;
    private final int index;

    public DoneOperation(TaskStorage taskStorage, int index) {
        this.taskStorage = taskStorage;
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        Task completed = this.taskStorage.completeTask(this.index);
        System.out.println("You have completed this task:\n" + completed);
    }
}
