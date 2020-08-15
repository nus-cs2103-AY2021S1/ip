package operation;

import task.Task;
import task.TaskStorage;

public class AddOperation extends Operation {
    private TaskStorage taskStorage;

    public AddOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        String taskString = String.join(" ", this.commands);
        Task task = Task.createTask(taskString);
        this.taskStorage.addTask(task);
        System.out.println("added: " + taskString);
    }
}