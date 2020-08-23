package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

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
    public String execute() {
        Task newTask = createTask();
        this.taskList.addTask(newTask);
        return "I have added the task:\n" + newTask + "\n"
                + String.format("You now have %d tasks.", this.taskList.getCurrCapacity());
    }
}