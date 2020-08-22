package command;

import task.Task;

import java.util.List;

/**
 * Remove task from taskList
 */
public class DeleteCommand extends Command {

    private final List<Task> taskList;
    private Task task;

    public DeleteCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    @Override
    public void execute() {
        this.taskList.remove(task);
        System.out.println("\t- Delete: " + task.toString());
    }

    @Override
    public void undo() {
        this.taskList.add(task);
        System.out.println("\t+ Undo Delete: " + task.toString());
    }
}
