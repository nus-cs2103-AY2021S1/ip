package command;

import task.Task;

/**
 * Mark task as done
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
}
