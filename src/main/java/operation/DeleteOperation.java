package operation;

import task.Task;
import task.TaskList;

public class DeleteOperation extends Operation {
    private final TaskList taskList;
    private final int index;

     public DeleteOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
     }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        Task removed = this.taskList.removeTask(this.index);
        String output = "Noted. I've removed this task:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.taskList.getCurrCapacity());
        System.out.println(output);
    }
}
