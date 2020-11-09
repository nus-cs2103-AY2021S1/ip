package duke.core.command;

import java.util.logging.Logger;

import duke.core.task.Task;
import duke.designpattern.command.ReversibleExecutable;

/**
 * Mark task as done
 */
public class DoneCommand implements ReversibleExecutable {

    private static final Logger logger = Logger.getLogger(DoneCommand.class.getName());

    private final Task task;

    /**
     * Create a DoneCommand to mark a Task as done/not done
     * @param task to be marked done/not done
     */
    public DoneCommand(Task task) {
        this.task = task;
        assert this.task != null;
    }

    /**
     * Mark task as completed
     */
    @Override
    public void execute() {
        task.setCompleted(true);
        System.out.println("# Done: " + task.toString());
        logger.info(DoneCommand.class.getSimpleName() + ": Done " + task.toString());
    }

    /**
     * Mark task as incomplete
     */
    @Override
    public void reverse() {
        task.setCompleted(false);
        System.out.println("# Undo Done: " + task.toString());
        logger.info(DoneCommand.class.getSimpleName() + ": Undo Done " + task.toString());
    }

}
