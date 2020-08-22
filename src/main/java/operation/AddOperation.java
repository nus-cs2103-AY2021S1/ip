package operation;

import task.Task;
import task.TaskList;

public abstract class AddOperation extends Operation {
    protected String description;
    protected TaskList taskList;

    AddOperation(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public abstract Task createTask();

    @Override
    public void execute() {
        Task newTask = createTask();
        this.taskList.addTask(newTask);
        System.out.println("I have added the task:\n" + newTask);
        String capacity = String.format("You now have %d tasks.",
                this.taskList.getCurrCapacity());
        System.out.println(capacity);
    }
}