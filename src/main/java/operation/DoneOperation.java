package operation;

import task.Task;
import task.TaskStorage;

public class DoneOperation extends Operation {
    private TaskStorage taskStorage;

    public DoneOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(this.commands[1]);
        this.taskStorage.completeTask(index);
        System.out.println("You have completed this task:");
        this.taskStorage.printTask(index);
    }
}
