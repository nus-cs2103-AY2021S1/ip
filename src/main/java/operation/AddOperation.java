package operation;

import task.Task;
import task.TaskStorage;

public abstract class AddOperation extends Operation {
    protected String description;
    protected TaskStorage taskStorage;

    AddOperation(String description, TaskStorage taskStorage) {
        this.description = description;
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public abstract Task createTask();

    @Override
    public void execute() {
        Task newTask = createTask();
        this.taskStorage.addTask(newTask);
        System.out.println("I have added the task:\n" + newTask);
        String capacity = String.format("You now have %d tasks.",
                this.taskStorage.getCurrCapacity());
        System.out.println(capacity);
    }
}