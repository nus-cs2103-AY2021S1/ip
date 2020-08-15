package operation;

import storage.TaskStorage;

public class ListOperation extends Operation {
    private TaskStorage taskStorage;
    public ListOperation(String operation, TaskStorage taskStorage) {
        super(operation);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        this.taskStorage.printTaskStorage();
    }
}
