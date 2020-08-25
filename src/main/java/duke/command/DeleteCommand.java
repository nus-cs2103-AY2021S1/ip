package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Remove duke.task from taskList
 */
public class DeleteCommand implements UndoCommand {

    private final List<Task> taskList;
    private Task task;

    public DeleteCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Delete the task referenced by this Command from the list
     */
    @Override
    public void execute() {
        this.taskList.remove(task);
        System.out.println("\t- Delete: " + task.toString());
    }

    /**
     * Adds the task removed by this Command into the list
     */
    @Override
    public void undo() {
        this.taskList.add(task);
        System.out.println("\t+ Undo Delete: " + task.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteCommand)) return false;
        DeleteCommand that = (DeleteCommand) o;
        return taskList.equals(that.taskList) &&
                task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, task);
    }
}
