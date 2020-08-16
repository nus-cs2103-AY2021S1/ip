package operation;

import task.TaskStorage;

public class ListOperation extends Operation {
    private final TaskStorage taskStorage;

    public ListOperation(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Here are your tasks:");
        this.taskStorage.printTaskStorage();
    }
}
