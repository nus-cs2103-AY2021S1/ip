package operation;

import task.TaskStorage;

public class ListOperation extends Operation {
    private TaskStorage taskStorage;

    public ListOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
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
