package operation;

import storage.TaskStorage;

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
        this.taskStorage.addTask(this.operation);
        System.out.println("added: " + this.operation);
    }
}