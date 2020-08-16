package operation;

import task.Task;
import task.TaskStorage;

public class DeleteOperation extends Operation {
    private final TaskStorage taskStorage;
    private final int index;

     public DeleteOperation(TaskStorage taskStorage, int index) {
        this.taskStorage = taskStorage;
        this.index = index;
     }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        Task removed = this.taskStorage.removeTask(this.index);
        String output = "Noted. I've removed this task:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.taskStorage.getCurrCapacity());
        System.out.println(output);
    }
}
