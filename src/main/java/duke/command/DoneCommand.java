package duke.command;

import duke.task.Task;

import java.util.Objects;

/**
 * Mark task as done
 */
public class DoneCommand implements ReversibleCommand {

    private final Task task;

    public DoneCommand(Task task) {
        this.task = task;
    }

    /**
     * Mark task as completed
     */
    @Override
    public void execute() {
        task.setCompleted(true);
        System.out.println("\t# Done: " + task.toString());
    }

    /**
     * Mark task as incomplete
     */
    @Override
    public void reverse() {
        task.setCompleted(false);
        System.out.println("\t# Undo Done: " + task.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoneCommand)) return false;
        DoneCommand that = (DoneCommand) o;
        return task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
