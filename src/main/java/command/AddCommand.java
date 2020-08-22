package command;

import task.Task;

import java.util.List;

/**
 * Add a task to taskList
 */
public class AddCommand extends Command{

    private final List<Task> taskList;
    private final Task task;

    public AddCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    @Override
    public void execute() {
        this.taskList.add(task);
        System.out.println("\t+ Add: " + task.toString());
    }

    @Override
    public void undo() {
        this.taskList.remove(task);
        System.out.println("\t- Undo Add: " + task.toString());
    }
}
