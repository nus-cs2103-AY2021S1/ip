package operation;

import task.Task;
import task.TaskStorage;

public class AddOperation extends Operation {

    private TaskStorage taskStorage;

    public AddOperation(String operation, TaskStorage taskStorage) {
        super(operation);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        Task task = Task.createTask(this.operation);
        this.taskStorage.addTask(task);
        System.out.println("added: " + this.operation);
    }
}