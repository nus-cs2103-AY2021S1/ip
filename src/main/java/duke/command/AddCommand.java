package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Add a duke.task to taskList
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AddCommand)) return false;
        AddCommand that = (AddCommand) obj;

        return taskList.equals(that.taskList) &&
                task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, task);
    }
}
