package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Remove duke.task from taskList
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DeleteCommand)) return false;
        DeleteCommand that = (DeleteCommand) obj;

        return taskList.equals(that.taskList) &&
                task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, task);
    }
}
