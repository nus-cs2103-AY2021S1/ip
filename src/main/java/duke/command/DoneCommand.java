package duke.command;

import duke.task.Task;

import java.util.Objects;

/**
 * Mark duke.task as done
 */
public class DoneCommand extends Command {

    private final Task task;

    public DoneCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        task.setCompleted(true);
        System.out.println("\t# Done: " + task.toString());
    }

    @Override
    public void undo() {
        task.setCompleted(false);
        System.out.println("\t# Undo Done: " + task.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoneCommand)) return false;
        DoneCommand that = (DoneCommand) obj;

        return task.equals(that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
