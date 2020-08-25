package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Add a task to taskList
 */
public class AddCommand implements ReversibleCommand {

    private final List<Task> taskList;
    private final Task task;

    public AddCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Add task to end of list
     */
    @Override
    public void execute() {
        this.taskList.add(task);
        System.out.println("\t+ Add: " + task.toString());
    }

    /**
     * Remove task from list
     */
    @Override
    public void reverse() {
        this.taskList.remove(task);
        System.out.println("\t- Undo Add: " + task.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddCommand)) return false;
        AddCommand that = (AddCommand) o;
        return taskList.equals(that.taskList) &&
                task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, task);
    }
}
