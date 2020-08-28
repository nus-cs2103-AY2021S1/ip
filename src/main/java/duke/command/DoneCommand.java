package duke.command;

import duke.task.Task;

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

}
